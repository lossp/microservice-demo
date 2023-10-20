package com.lossp.microservice.transactionimpl.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TransactionTestController {

    @PostConstruct
    public void printOut() {
        System.out.println("Transaction initialize successfully");
    }
}
