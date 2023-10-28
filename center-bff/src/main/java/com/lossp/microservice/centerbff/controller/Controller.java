package com.lossp.microservice.centerbff.controller;

import com.lossp.microservice.centerbff.mqservice.PaymentMqService;
import com.lossp.microservice.centerbff.rpcService.PaymentRpcService;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanBuilder;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final Tracer tracer;


    private final PaymentRpcService paymentRpcService;
    private final PaymentMqService paymentMqService;

    public Controller(Tracer tracer, PaymentRpcService paymentRpcService, PaymentMqService paymentMqService) {
        this.tracer = tracer;
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
        Span span = tracer.spanBuilder("say-hi").startSpan();
        span.setAttribute("Age", 15);
        span.setAttribute("Name", "rci");
        logger.info("----- entering");
        paymentMqService.startPayment();
        span.end();
        return "Yes!";
    }
    @GetMapping("/sequential")
    public String sequentialProcess() {
        paymentMqService.startPaymentEventsInOrder();
        return "Yes!";
    }

    @GetMapping("/delay")
    public String delayMessage() {
        paymentMqService.startDelayPaymentEvent();
        return "Delay Message";
    }

}
