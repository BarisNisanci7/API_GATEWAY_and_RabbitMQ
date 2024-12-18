package com.rabbitmq.service;

import com.rabbitmq.dto.PaymentRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PaymentService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPaymentRequest() {
        PaymentRequest request = new PaymentRequest();
        request.setUser("123");
        request.setPaymentType("USD");
        request.setCardNo("12345678901");

        rabbitTemplate.convertAndSend("PaymentQueue", request);
        System.out.println("PaymentRequest sent: " + request);
    }
}
