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
    RabbitTemplate rabbitTemplateForProducer(ConnectionFactory connectionFactory,
                                  MessageConverter messageConverterForProducer) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverterForProducer);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter messageConverterForProducer() {
        return new Jackson2JsonMessageConverter();
    }

}