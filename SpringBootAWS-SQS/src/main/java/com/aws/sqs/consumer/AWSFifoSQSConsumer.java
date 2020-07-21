package com.aws.sqs.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.aws.sqs.domin.SpringWorld;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AWSFifoSQSConsumer {

	@Autowired Environment env;
	@Autowired AmazonSQS amazonSQS;
	@SqsListener("spring-boot-aws.fifo")
	 public void readMessage(String msg) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		SpringWorld json=objectMapper.readValue(msg, SpringWorld.class);
		log.debug(":::::::::: Message received from AWS SQS FIFO queue :::::::::"+msg);
		log.debug(":::::::::: Object ID ::::::::"+json.getId());
		log.debug(":::::::::: Object Env ::::::::"+json.getEnv());
		log.debug(":::::::::: Object Value ::::::::"+json.getValue());
	 }
}
