package com.lossp.microservice.centerbff.mqservice;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PaymentMqService {
    private static final String START_PAYMENT_TOPIC = "payment-topic";
    private static final String SEQUENTIAL_TOPIC = "payment-sequential-topic";
    private final RocketMQTemplate rocketMQTemplate;

    public PaymentMqService(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void startPayment() {
        rocketMQTemplate.asyncSend(START_PAYMENT_TOPIC, "Here is the request from bff", new SendCallback() {
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
    }

    public void startPaymentEventsInOrder() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        String transactionId = LocalDateTime.now().format(format);
        List<String> transactionEvents = List.of("start transaction", "waiting for paying", "paid the money", "fulfil the transaction");
        for (String event:transactionEvents) {
            rocketMQTemplate.syncSendOrderly(SEQUENTIAL_TOPIC, event, transactionId);
        }
    }
}