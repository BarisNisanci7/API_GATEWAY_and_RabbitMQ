package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue paymentQueue() {
        return new Queue("paymentQueue", true);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue("notificationQueue", true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.addTrustedPackages("com.rabbitmq.dto");
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }

}
