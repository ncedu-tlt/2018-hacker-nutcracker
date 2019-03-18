package com.ncspring.ncspring.kafka.listener;

import com.ncspring.ncspring.model.Person;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "personKafkaListenerFactory")
    public void consumeJson(Person person) {
        System.out.println("Consumed JSON Message: " + person);
    }
}
