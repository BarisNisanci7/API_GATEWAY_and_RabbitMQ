package com.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(queues = "NotificationQueue")
    public void sendNotification(String notificationPayload) {
        try {
            System.out.println("Notification sent: " + notificationPayload);
        } catch (Exception e) {
            System.err.println("Error sending notification: " + e.getMessage());
        }
    }

}
