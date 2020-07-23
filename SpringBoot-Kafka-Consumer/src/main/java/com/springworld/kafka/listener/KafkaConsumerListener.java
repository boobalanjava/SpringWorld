package com.springworld.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springworld.kafka.model.DataInfo;

@Service
public class KafkaConsumerListener {

    @KafkaListener(topics = "springword-kafka-topic", groupId = "springworld_group",
            containerFactory = "dataInfoKafkaListenerFactory")
    public void consumeJson(DataInfo user) {
        System.out.println("Consumed Message: " + user);
    }
}
