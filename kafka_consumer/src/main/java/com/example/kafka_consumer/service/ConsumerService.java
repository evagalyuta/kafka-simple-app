package com.example.kafka_consumer.service;

import com.example.kafka_consumer.entity.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "messages", groupId = "group_json", containerFactory = "kafkaLister")
    public void post(Message message){
        System.out.println("Consumed message: " + message);
    }
}
