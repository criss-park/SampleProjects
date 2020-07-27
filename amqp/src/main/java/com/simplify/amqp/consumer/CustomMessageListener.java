package com.simplify.amqp.consumer;

import com.simplify.amqp.custom.CustomMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageListener {

    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(final Message message) {
        System.out.println("spring-boot : " + message);
    }

    @RabbitListener(queues = "spring-boot2")
    public void receiveMessage(final CustomMessage message) {
        System.out.println("spring-boot2 : " + message);
    }

}