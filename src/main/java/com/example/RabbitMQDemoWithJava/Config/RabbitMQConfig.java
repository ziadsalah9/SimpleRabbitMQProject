package com.example.RabbitMQDemoWithJava.Config;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //1- Queue
    // Spring Bean for RabbitMQ Queue


    @Value("${rabbitmq.queue.name}")
    private String queuename;

    @Value("${rabbitmq.Exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey ;

    @Bean
    public Queue Queue(){

        return new Queue(queuename);
    }



    //2-Exchange
    // Spring Bean for RabbitMQ Exchange

    @Bean
    public TopicExchange Exchange(){
        return new TopicExchange(exchange);
    }


    //2-Binding between Exchange And Queue using routingKey

    @Bean
    public Binding Binding(){

        return BindingBuilder.bind(Queue()).to(Exchange()).with(routingKey);
    }


    @Bean
    public Queue Queue2(){
        return new Queue("qu2");
    }
    @Bean
    public Binding Binding2(){
        return BindingBuilder.bind(Queue2()).to(Exchange()).with(jsonRoutingKey);
    }


    @Bean
    public MessageConverter MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory);

        rabbitTemplate.setMessageConverter(MessageConverter());
        return rabbitTemplate;

    }


}
