package com.lossp.microservice.paymentimpl.service.port;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.lossp.microservice.paymentimpl.service.model.PaymentProcessDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyPaymentAdaptor {
    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyPaymentAdaptor.class);

    @SentinelResource(value = "processThePayment", blockHandler = "processThePayment_inBlock")
    public String processThePayment(PaymentProcessDTO paymentProcessDTO) {
        String result = JSON.toJSONString(paymentProcessDTO);
        logger.info(result);
        return result;
    }

    public String processThePayment_inBlock(PaymentProcessDTO paymentProcessDTO) {
        return "Exceed";
    }
}
