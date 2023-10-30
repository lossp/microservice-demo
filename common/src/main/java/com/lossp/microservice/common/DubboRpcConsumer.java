package com.lossp.microservice.common;

import com.alibaba.fastjson.JSON;
import io.opentelemetry.api.trace.Span;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Activate(group = {CommonConstants.CONSUMER})
public class DubboRpcConsumer implements Filter {
    Logger logger = LoggerFactory.getLogger(DubboRpcConsumer.class);


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String tracingSpan = RpcContext.getClientAttachment().getAttachment("TRACING_SPAN");
        Span span = JSON.parseObject(tracingSpan, Span.class);
        logger.info("[Dubbo {}.{}]. --Span is: {}",invoker.getInterface(), invocation.getMethodName(), span);
        ContextSession.setSpan(span);
        try {
            return invoker.invoke(invocation);
        } finally {
            span.end();
            afterHandle();
        }
    }

    private void afterHandle() {
        ContextSession.remove();
    }
}
