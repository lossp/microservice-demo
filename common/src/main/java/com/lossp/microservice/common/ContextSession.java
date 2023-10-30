package com.lossp.microservice.common;

import io.opentelemetry.api.trace.Span;

public class ContextSession {
    private static final ThreadLocal<Span> spanThreadLocal = new ThreadLocal<>();

    public static Span getSpan() {
        return spanThreadLocal.get();
    }

    public static void setSpan(Span span) {
        spanThreadLocal.set(span);
    }

    public static void remove() {
        spanThreadLocal.remove();
    }
}
