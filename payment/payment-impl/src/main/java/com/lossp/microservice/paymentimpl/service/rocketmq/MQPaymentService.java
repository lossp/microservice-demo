package com.lossp.microservice.paymentimpl.service.rocketmq;


import com.example.paymentintf.payment.dto.PaymentCreateRequestDTO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "payment-topic",
        consumerGroup = "payment-consume-group"
)
public class MQPaymentService implements RocketMQListener<PaymentCreateRequestDTO> {

    @Override
    public void onMessage(PaymentCreateRequestDTO o) {
        String string = o.toString();
        System.out.println("===" + string);
        System.out.println("this is the payment service. the rocketmq message has successfully reached its destination.");
    }
}
