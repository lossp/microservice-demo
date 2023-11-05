package com.lossp.microservice.transactionimpl;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InventoryService {
    @PostConstruct
    public void printTest() {
        System.out.println("---------");
    }
}
