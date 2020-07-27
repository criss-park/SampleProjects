package com.simplify.amqp.producer;

import com.simplify.amqp.custom.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner2 implements CommandLineRunner {

    private static final String topicExchange = "spring-boot2-exchange";

    private final RabbitTemplate rabbitTemplate3;

    public Runner2(RabbitTemplate rabbitTemplate3) {
        this.rabbitTemplate3 = rabbitTemplate3;
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending message with Custom Message...");
        CustomMessage message = new CustomMessage("Hello Message!", 1, true);
        rabbitTemplate3.convertAndSend(topicExchange, "foo.bar.2.baz", message);
    }

}