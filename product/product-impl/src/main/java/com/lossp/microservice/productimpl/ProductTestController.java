package com.lossp.microservice.productimpl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ProductTestController {

    @PostConstruct
    public void printOut() {
        System.out.println("Product initialize successfully");
    }
}
