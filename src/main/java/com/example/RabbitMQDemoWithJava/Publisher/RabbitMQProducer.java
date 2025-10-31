package com.example.RabbitMQDemoWithJava.Publisher;

import com.example.RabbitMQDemoWithJava.Models.Department;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.Flow;

@Service
public class RabbitMQProducer {


    @Value("${rabbitmq.Exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKey;

    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);
    public void SendMessage(String message){

        logger.info(String.format("Sending Message  -> %s",message));
        rabbitTemplate.convertAndSend(exchange,routingKey,message);

    }

//    public void SendMessage2(String message){
//        logger.info(String.format("Sending Message  -> %s",message));
//        rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,message);
//    }





//    @Autowired
//    private Jackson2JsonMessageConverter jsonConverter;
//
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setMessageConverter(jsonConverter);
//    }
//
//    public void SendMessage2(Department department){
//        logger.info(String.format("Sending Message  -> %s",department.getName()));
//        rabbitTemplate.convertAndSend(exchange,routingKey,department);
//
//    }


}
