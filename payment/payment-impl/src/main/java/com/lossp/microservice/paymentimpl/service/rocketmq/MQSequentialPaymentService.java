package com.lossp.microservice.paymentimpl.service.rocketmq;


import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "payment-sequential-topic",
        consumerGroup = "payment-consume-group-orderly",
        consumeMode = ConsumeMode.ORDERLY
)
//需要设置和 MQPaymentService 不一致的consumerGroup，否则会出现无法注册上rocketmq情况，导致消息会无法消费情况
public class MQSequentialPaymentService implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
