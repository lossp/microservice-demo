package com.lossp.microservice.transactionimpl;


import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InventoryService {
    private final RedissonClient redissonClient;

    public InventoryService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @PostConstruct
    public void printTest() {
        redissonClient.getBucket("Test Phase").set("The tt");
        System.out.println("---------");
        String o =(String) redissonClient.getBucket("Test Phase").get();
        System.out.println("-======0" + o);
    }
}
