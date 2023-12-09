package com.lossp.microservice.centerbff.controller;

import com.lossp.microservice.centerbff.mqservice.PaymentMqService;
import com.lossp.microservice.centerbff.rpcService.PaymentRpcService;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final PaymentRpcService paymentRpcService;
    private final PaymentMqService paymentMqService;

    public Controller(PaymentRpcService paymentRpcService, PaymentMqService paymentMqService) {
        this.paymentRpcService = paymentRpcService;
        this.paymentMqService = paymentMqService;
    }

    @GetMapping("/home")
    public String homePage() {
        paymentRpcService.testPayment();
        return "Welcome";
    }

    @GetMapping("/sentinel")
    public String sentinelTest() {
        return paymentRpcService.testSentinelApi();
    }

    @GetMapping("/rocketMqTest")
    public String toRocketMQ() {
        logger.info("----- entering");
        paymentMqService.startPayment();
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
