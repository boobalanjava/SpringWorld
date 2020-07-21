package com.aws.sqs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aws.sqs.consumer.AWSFifoSQSConsumer;
import com.aws.sqs.producer.AWSFifoSQSProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class AWSSQSController {

	@Autowired AWSFifoSQSProducer producer;
	@Autowired AWSFifoSQSConsumer consumer;
	
	@GetMapping("/pushMessage")
	public String sendMessageToSQS() throws JsonProcessingException {
		producer.pushMessage();
		return "Pushed Message to AWS SQS FIFO";
	}
	
}
