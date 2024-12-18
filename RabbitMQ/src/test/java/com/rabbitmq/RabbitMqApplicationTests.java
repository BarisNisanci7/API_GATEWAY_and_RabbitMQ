package com.rabbitmq;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.RabbitMQContainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RabbitMqApplicationTests {

    static RabbitMQContainer rabbitMQContainer;

    private static CachingConnectionFactory connectionFactory;
    private static RabbitTemplate rabbitTemplate;

    @BeforeAll
    static void setUp() {
        rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-management");
        rabbitMQContainer.start();

        connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMQContainer.getHost());
        connectionFactory.setPort(rabbitMQContainer.getAmqpPort());
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.execute(channel -> {
            channel.queueDeclare("notificationQueue", true, false, false, null);
            channel.queueDeclare("paymentQueue", true, false, false, null);
            return null;
        });
    }

    @Test
    void testSendNotificationQueue() {
        String notification = "Test Notification";
        rabbitTemplate.convertAndSend("notificationQueue", notification);
        String receivedMessage = (String) rabbitTemplate.receiveAndConvert("notificationQueue");
        assertNotNull(receivedMessage, "Message should not be null");
    }

    @Test
    void testSendPaymentQueue() {
        String payment = "Test Payment";
        rabbitTemplate.convertAndSend("paymentQueue", payment);
        String receivedMessage = (String) rabbitTemplate.receiveAndConvert("paymentQueue");
        assertNotNull(receivedMessage, "Message should not be null");
    }
}
