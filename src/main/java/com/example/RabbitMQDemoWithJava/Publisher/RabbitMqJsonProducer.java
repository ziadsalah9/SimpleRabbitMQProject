package com.example.RabbitMQDemoWithJava.Publisher;

import com.example.RabbitMQDemoWithJava.Config.RabbitMQConfig;
import com.example.RabbitMQDemoWithJava.Models.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqJsonProducer {
    @Value("${rabbitmq.Exchange.name}")
    private String exchange;


    @Value("${rabbitmq.json.routing.key.name}")
    private String jsonRoutingKey;


    Logger logger = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Department department) {

        logger.info(String.format("Sending Message to RabbitMQ with routing key: %s", department));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, department);

    }



}
