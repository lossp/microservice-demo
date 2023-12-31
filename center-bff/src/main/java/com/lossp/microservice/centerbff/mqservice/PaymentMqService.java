package com.lossp.microservice.centerbff.mqservice;

import com.example.paymentintf.payment.dto.PaymentCreateRequestDTO;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentMqService {
    private static final String START_PAYMENT_TOPIC = "payment-topic";
    private static final String SEQUENTIAL_TOPIC = "payment-sequential-topic";
    private final RocketMQTemplate rocketMQTemplate;
    private final Tracer tracer;

    public PaymentMqService(RocketMQTemplate rocketMQTemplate, Tracer tracer) {
        this.rocketMQTemplate = rocketMQTemplate;
        this.tracer = tracer;
    }

    private String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        return formatter.format(dateTime);
    }

    public void startPayment() {
        Span span = tracer.spanBuilder("startPayment").startSpan();
        try (Scope s = span.makeCurrent()) {
            formatTime(LocalDateTime.now());
            PaymentCreateRequestDTO dto = new PaymentCreateRequestDTO();
            dto.setPaymentId("payment-001");
            dto.setAmount(BigDecimal.valueOf(100L));
            dto.setChannel("WechatPay");
            rocketMQTemplate.asyncSend(START_PAYMENT_TOPIC, dto, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("Now is success");
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println(throwable.getLocalizedMessage());
                    System.out.println("Now is failed");
                }
            });
        } finally {
            span.end();
        }
    }

    public void startPaymentEventsInOrder() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        String transactionId = LocalDateTime.now().format(format);
        List<String> transactionEvents = List.of("start transaction", "waiting for paying", "paid the money", "fulfil the transaction");
        for (String event:transactionEvents) {
            rocketMQTemplate.syncSendOrderly(SEQUENTIAL_TOPIC, event, transactionId);
        }
    }

    public void startDelayPaymentEvent() {
        // TODO need to wrap it up. using the GenericMessage
        PaymentCreateRequestDTO dto = new PaymentCreateRequestDTO();
        dto.setPaymentId("payment-001");
        dto.setAmount(BigDecimal.valueOf(100L));
        dto.setChannel("WechatPay");
        GenericMessage<PaymentCreateRequestDTO> genericMessage = new GenericMessage<>(dto);

        rocketMQTemplate.syncSend(START_PAYMENT_TOPIC, genericMessage, 1000, 3);
    }
}