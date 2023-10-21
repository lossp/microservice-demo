package com.lossp.microservice.paymentimpl.service;


import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class PaymentService implements IPaymentService {
    @Override
    public void testMethod() {
        System.out.println("this is the payment-impl package. You have successfully reached the target");
    }
}
