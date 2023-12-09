package com.lossp.microservice.paymentimpl.service;


import com.example.paymentintf.payment.IPaymentService;
import com.lossp.microservice.paymentimpl.service.model.PaymentProcessDTO;
import com.lossp.microservice.paymentimpl.service.port.ThirdPartyPaymentAdaptor;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class PaymentService implements IPaymentService {

    private final ThirdPartyPaymentAdaptor thirdPartyPaymentAdaptor;

    public PaymentService(ThirdPartyPaymentAdaptor thirdPartyPaymentAdaptor) {
        this.thirdPartyPaymentAdaptor = thirdPartyPaymentAdaptor;
    }

    @Override
    public void processPayment() {
        System.out.println("This is the payment impl package. You have successfully reached the target");
    }

    @Override
    public String process3rdPartyPayment() {
        // mock
        PaymentProcessDTO requestDto = new PaymentProcessDTO();
        return thirdPartyPaymentAdaptor.processThePayment(requestDto);
    }
}
