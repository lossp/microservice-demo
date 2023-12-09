package com.lossp.microservice.paymentimpl.service.model;

import java.math.BigInteger;

public class PaymentProcessDTO {
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String orderNumber;
    private BigInteger amount;
    private String username;
}
