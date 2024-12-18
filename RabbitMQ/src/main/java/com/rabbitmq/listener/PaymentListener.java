package com.rabbitmq.listener;

import com.rabbitmq.service.NotificationService;
import com.rabbitmq.dto.PaymentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {

    private final NotificationService notificationService;

    public PaymentListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "PaymentQueue")
    public void receiveMessage(PaymentRequest paymentRequest) {
        System.out.println("Received PaymentRequest: " + paymentRequest);
    }

    @RabbitListener(queues = "paymentQueue")
    public void processPayment(String paymentPayload) {
        try {
            System.out.println("Payment received: " + paymentPayload);

            notificationService.sendNotificationToQueue("Your payment has been processed.");
        } catch (Exception e) {
            System.err.println("Error processing payment: " + e.getMessage());
        }
    }

}