package com.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.testcontainers.containers.RabbitMQContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RabbitMqTestContainersTest {

    @Test
    void testRabbitMQConnection() {
        RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-management");
        rabbitMQContainer.start();

        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitMQContainer.getHost());
        factory.setPort(rabbitMQContainer.getAmqpPort());

        assertTrue(factory.createConnection().isOpen(), "Connection to Testcontainers RabbitMQ should be open");
        rabbitMQContainer.stop();
    }
}
