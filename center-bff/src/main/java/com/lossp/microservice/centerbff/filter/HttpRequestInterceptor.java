package com.lossp.microservice.centerbff.filter;

import com.lossp.microservice.common.ContextSession;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(HttpRequestInterceptor.class);

    private final Tracer tracer;

    public HttpRequestInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandle");
        Span span = tracer.spanBuilder(request.getRequestURI()).startSpan();
        ContextSession.setSpan(span);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        Span span = ContextSession.getSpan();
        logger.info("postHandle");
        span.end();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ContextSession.remove();
        logger.info("afterCompletion");
    }
}
