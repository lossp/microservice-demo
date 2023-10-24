package com.lossp.microservice.centerbff.controller;

import com.lossp.microservice.centerbff.mqservice.PaymentMqService;
import com.lossp.microservice.centerbff.rpcService.PaymentRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final PaymentRpcService paymentRpcService;
    private final PaymentMqService paymentMqService;

    public Controller(PaymentRpcService paymentRpcService, PaymentMqService paymentMqService) {
        this.paymentRpcService = paymentRpcService;
        this.paymentMqService = paymentMqService;
    }

    @GetMapping("/home")
    public String homePage() {
        paymentRpcService.testPayment();
        return "Welcome";
    }

    @GetMapping("/rocketMqTest")
    public String toRocketMQ() {
        paymentMqService.startPayment();
        return "Yes!";
    }

}
