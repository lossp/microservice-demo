package com.lossp.microservice.centerbff;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class CenterBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterBffApplication.class, args);
    }

}
