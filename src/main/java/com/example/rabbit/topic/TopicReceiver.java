package com.example.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "topic.messages",containerFactory = "customContainerFactory")
@RabbitListener(queues = "topic.messageses")
public class TopicReceiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println(Thread.currentThread().getName()+ "    Topic Receiver1  : " + message);
    }

}
