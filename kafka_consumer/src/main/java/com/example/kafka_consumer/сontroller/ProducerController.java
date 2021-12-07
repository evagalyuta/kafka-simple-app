package com.example.kafka_consumer.сontroller;

import com.example.kafka_consumer.entity.Message;
import com.example.kafka_consumer.service.ProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam String message, @RequestParam Integer age) {
        producerService.produce(new Message(message, age));
        return "OK";
    }
}
