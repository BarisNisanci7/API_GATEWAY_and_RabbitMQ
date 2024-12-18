package com.rabbitmq.dto;

import java.io.Serializable;


public class PaymentRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user;
    private String paymentType;
    private String cardNo;

    // Getters and Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
