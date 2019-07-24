package com.example.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author :mp
 * topic
在exchange中routing key 和binding key相匹配就可以绑定成功，可以一对一也可一对多。
bingding key 可以存在两种特殊字符，# :匹配一个或多个单词。 *:匹配一个单词。
 * 2019年5月31日
 */
@Configuration
public class TopicRabbitConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";
    final static String messageses = "topic.messageses";
    final static String allMessage = "topic.allMessage";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages);
    }

    @Bean
    public Queue queueMessageses() {
        return new Queue(TopicRabbitConfig.messageses);
    }
    @Bean
    public Queue allMessage() {
        return new Queue(TopicRabbitConfig.allMessage);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeAllMessage(Queue allMessage, TopicExchange exchange) {
        return BindingBuilder.bind(allMessage).to(exchange).with("topic.allMessage");
    }
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        //return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.messages");
    }


    @Bean
    Binding bindingExchangeMessageses(Queue queueMessageses, TopicExchange exchange) {
        //return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
        return BindingBuilder.bind(queueMessageses).to(exchange).with("topic.messageses");
    }
//    @Bean("customContainerFactory")
//    public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConcurrentConsumers(50);  //设置线程数
//        factory.setMaxConcurrentConsumers(50); //最大线程数
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }
}
