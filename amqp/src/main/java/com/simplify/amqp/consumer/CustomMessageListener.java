package com.simplify.amqp.consumer;

import com.simplify.amqp.Constants;
import com.simplify.amqp.custom.CustomMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CustomMessageListener {

    @RabbitListener(queues = Constants.QUEUE_NAME)
    public void receiveMessage(final Message message) {
        System.out.println(Constants.QUEUE_NAME + " : " + message);
    }

    @RabbitListener(queues = Constants.QUEUE_NAME_FOR_CUSTOM_MESSAGE)
    public void receiveMessage(final CustomMessage customMessage) {
        System.out.println(Constants.QUEUE_NAME_FOR_CUSTOM_MESSAGE + " : " + customMessage);
    }

}