package com.simplify.amqp.producer;

import com.simplify.amqp.Constants;
import com.simplify.amqp.custom.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE_NAME, "foo.bar.baz", "Hello Message!");
    }

}