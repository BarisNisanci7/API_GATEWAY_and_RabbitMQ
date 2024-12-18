package com.rabbitmq.controller;

import com.rabbitmq.dto.PaymentRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PaymentController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest paymentRequest) {
        rabbitTemplate.convertAndSend("paymentQueue", paymentRequest);
        return ResponseEntity.ok("Payment request sent to queue.");
    }
}
