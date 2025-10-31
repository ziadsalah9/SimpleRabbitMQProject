package com.example.RabbitMQDemoWithJava.Controllers;

import com.example.RabbitMQDemoWithJava.Models.Department;
import com.example.RabbitMQDemoWithJava.Publisher.RabbitMQProducer;
import com.example.RabbitMQDemoWithJava.Publisher.RabbitMqJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {


    @Autowired
    private RabbitMQProducer producer;
    @Autowired
    private RabbitMqJsonProducer jsonProducer;


    //http://localhost:9099/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){

        producer.SendMessage(message);
        return ResponseEntity.ok("message sent to rabbitMQ ... ");

    }


    @PostMapping("/publishobject")
    public ResponseEntity<String> sendObject( @RequestBody Department department){


        jsonProducer.sendMessage(department);
        return ResponseEntity.ok("Json sent to rabbitMQ ... ");

    }




}
