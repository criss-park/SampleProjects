package com.simplify.amqp.producer;

import com.simplify.amqp.Constants;
import com.simplify.amqp.custom.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerForCustomMessage implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplateForProducer;

    public RunnerForCustomMessage(RabbitTemplate rabbitTemplateForProducer) {
        this.rabbitTemplateForProducer = rabbitTemplateForProducer;
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending message with Custom Message...");
        CustomMessage message = new CustomMessage("Hello Message!", 1, true);
        rabbitTemplateForProducer.convertAndSend(Constants.TOPIC_EXCHANGE_NAME_FOR_CUSTOM_MESSAGE, "foo.bar.2.baz", message);
    }

}