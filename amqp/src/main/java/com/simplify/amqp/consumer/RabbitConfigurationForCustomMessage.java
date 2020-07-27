package com.simplify.amqp.consumer;

import com.simplify.amqp.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfigurationForCustomMessage {

    @Bean
    Queue queueForCustomMessage() {
        return new Queue(Constants.QUEUE_NAME_FOR_CUSTOM_MESSAGE, false);
    }

    @Bean
    TopicExchange topicExchangeForCustomMessage() {
        return new TopicExchange(Constants.TOPIC_EXCHANGE_NAME_FOR_CUSTOM_MESSAGE);
    }

    @Bean
    Binding binding2(Queue queueForCustomMessage, TopicExchange topicExchangeForCustomMessage) {
        return BindingBuilder.bind(queueForCustomMessage).to(topicExchangeForCustomMessage).with(Constants.ROUTING_KEY_FOR_CUSTOM_MESSAGE);
    }

    @Bean
    RabbitTemplate rabbitTemplate2(ConnectionFactory connectionFactory,
                                  MessageConverter messageConverterForCustomMessage) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverterForCustomMessage);
        return rabbitTemplate;
    }

    @Bean
    MessageConverter messageConverterForCustomMessage() {
        return new Jackson2JsonMessageConverter();
    }
}