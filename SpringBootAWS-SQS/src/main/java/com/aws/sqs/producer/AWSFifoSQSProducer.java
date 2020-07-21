package com.aws.sqs.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.aws.sqs.domin.SpringWorld;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AWSFifoSQSProducer {

	private static final String CLOUD_AWS_END_POINT_SQS_URL = "cloud.aws.endPoint.sqs.Url";
	private static final String SPRING_BOOT_AWS_FIFO_GROUP_ID = "springBoot-aws-fifo-groupId";
	@Autowired Environment env;
	@Autowired AmazonSQS amazonSQS;

	public void pushMessage() throws JsonProcessingException {

		SpringWorld spring = new SpringWorld();
		spring.setId(456);
		spring.setValue("testAWS");
		spring.setEnv("DEV"); 
		ObjectMapper objectMapper = new ObjectMapper();
		String json=objectMapper.writeValueAsString(spring);

		SendMessageRequest sendMessageFifoQueue = new SendMessageRequest(env.getProperty(CLOUD_AWS_END_POINT_SQS_URL),json).withMessageGroupId(SPRING_BOOT_AWS_FIFO_GROUP_ID);
		amazonSQS.sendMessage(sendMessageFifoQueue);
		log.debug("::::::::: Pushed Message to AWS SQS FIFO Queue :::::::::::"); 
	}


}
