package com.example.kafka_consumer.service;

import com.example.kafka_consumer.entity.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProducerService {
    private KafkaTemplate<String, Message> kafkaTemplate;

    public ProducerService() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(JsonSerializer.TYPE_MAPPINGS, "message:com.example.kafka_consumer.entity.Message");

        kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(config));
    }

    public void produce(Message message) {
        System.out.println("Producing the message from consumer: " + message);
        kafkaTemplate.send("messages_consumer", message);
    }
}
