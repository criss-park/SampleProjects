package com.simplify.amqp.producer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigurationForProducer {

    @Bean
    RabbitTemplate rabbitTemplate3(ConnectionFactory connectionFactory,
                                  MessageConverter messageConverter2) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter2);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter messageConverter3() {
        return new Jackson2JsonMessageConverter();
    }

}