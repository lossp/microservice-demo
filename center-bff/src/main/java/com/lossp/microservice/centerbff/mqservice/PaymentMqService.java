package com.lossp.microservice.centerbff.mqservice;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentMqService {
    private static final String START_PAYMENT_TOPIC = "payment-topic";
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
}