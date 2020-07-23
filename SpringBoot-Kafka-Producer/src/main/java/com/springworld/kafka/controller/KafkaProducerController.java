package com.springworld.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springworld.kafka.model.DataInfo;

@RestController
@RequestMapping("/kafkaProducer")
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, DataInfo> kafkaTemplate;

    private static final String TOPIC = "springword-kafka-topic";

    @GetMapping("/publishMessage/{msg}")
    public String post(@PathVariable("msg") final String msg) {

        kafkaTemplate.send(TOPIC, new DataInfo(1, "SpringBootKafka", "sample123"));

        return "Message Published successfully in Kafka topic";
    }
}
