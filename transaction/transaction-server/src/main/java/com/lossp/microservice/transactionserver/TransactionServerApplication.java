package com.lossp.microservice.transactionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.lossp.microservice.transactionimpl")
public class TransactionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServerApplication.class, args);
    }

}
