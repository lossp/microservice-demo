package com.lossp.microservice.common;

import io.opentelemetry.api.trace.Span;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class DubboRpcProducer implements Filter {

    Logger logger = LoggerFactory.getLogger(DubboRpcProducer.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        logger.info("[Dubbo {}.{}]. --Span is: {}",invoker.getInterface(), invocation.getMethodName(), ContextSession.getSpan());
        preHandle();
        try {
            return invoker.invoke(invocation);
        } finally {
            // clear
            afterHandle();
        }
    }

    private void afterHandle() {
        ContextSession.remove();
    }

    private void preHandle() {
        Span span = ContextSession.getSpan();
        RpcContext.getClientAttachment().setAttachment("TRACING_SPAN", span);
    }

}
