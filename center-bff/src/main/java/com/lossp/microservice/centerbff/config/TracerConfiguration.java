package com.lossp.microservice.centerbff.config;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TracerConfiguration {


    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Tracer tracer() {
        return GlobalOpenTelemetry.getTracer(applicationName);
    }
}
