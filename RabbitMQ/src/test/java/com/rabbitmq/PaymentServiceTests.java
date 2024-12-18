package com.rabbitmq;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.testcontainers.containers.RabbitMQContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceTests {

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
            channel.queueDeclare("paymentQueue", true, false, false, null);
            return null;
        });
    }

    @Test
    void testPaymentQueueSend() {
        String testMessage = "Test Payment";

        rabbitTemplate.convertAndSend("paymentQueue", testMessage);

        String receivedMessage = (String) rabbitTemplate.receiveAndConvert("paymentQueue");
        assertEquals(testMessage, receivedMessage, "Mesaj kuyruğa doğru şekilde gönderilmeli ve alınmalı");
    }

    @AfterAll
    static void tearDown() {
        if (rabbitMQContainer != null) {
            rabbitMQContainer.stop();
        }
    }

}
