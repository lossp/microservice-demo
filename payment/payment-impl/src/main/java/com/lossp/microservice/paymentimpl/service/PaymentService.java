package com.lossp.microservice.paymentimpl.service;


import com.example.paymentintf.payment.IPaymentService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class PaymentService implements IPaymentService {
    @Override
    public void processPayment() {
        System.out.println("This is the payment impl package. You have successfully reached the target");
    }
}
