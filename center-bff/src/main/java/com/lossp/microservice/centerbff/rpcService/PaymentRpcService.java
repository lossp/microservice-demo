package com.lossp.microservice.centerbff.rpcService;

import com.example.paymentintf.payment.IPaymentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class PaymentRpcService {
    @DubboReference
    IPaymentService paymentService;

    public void testPayment() {
        paymentService.processPayment();
    }
}
