package com.lossp.microservice.centerbff.controller;

import com.lossp.microservice.centerbff.rpcService.PaymentRpcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final PaymentRpcService paymentRpcService;

    public Controller(PaymentRpcService paymentRpcService) {
        this.paymentRpcService = paymentRpcService;
    }

    @GetMapping("/home")
    public String homePage() {
        paymentRpcService.testPayment();
        return "Welcome";
    }
}
