package com.example.RabbitMQDemoWithJava.Consumer;

import com.example.RabbitMQDemoWithJava.Models.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);


    @RabbitListener(queues = {"qu2"})
    public void ConsumeMessage(Department department)
    {

        log.info(String.format("data is -> %s", department.toString()));
        //  log.info(String.format("Id from RabbitMQ is -> ",department.getId(),"Name from RabbitMQ is -> ", department.getName()));

    }



}
