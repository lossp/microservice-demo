package com.lossp.microservice.centerbff.config;

import com.lossp.microservice.centerbff.filter.HttpRequestInterceptor;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorsConfig extends WebMvcConfigurationSupport {

    private final Tracer tracer;

    public InterceptorsConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpRequestInterceptor(tracer));
        super.addInterceptors(registry);
    }
}
