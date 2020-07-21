package com.aws.sqs.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationConfig {

	private static final String CONTENT_BASED_DEDUPLICATION = "ContentBasedDeduplication";
	private static final String FIFO_QUEUE = "FifoQueue";
	private static final String SPRING_BOOT_AWS_FIFO_QUEUE = "spring-boot-aws.fifo";
	private static final String CLOUD_AWS_CREDENTIALS_SECRET_KEY = "cloud.aws.credentials.secret-key";
	private static final String CLOUD_AWS_CREDENTIALS_ACCESS_KEY = "cloud.aws.credentials.access-key";
	
	@Autowired Environment env;
	@Bean
	@Primary
	public AmazonSQS  amazonSQSAsync() {
		return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(env.getProperty(CLOUD_AWS_CREDENTIALS_ACCESS_KEY), env.getProperty(CLOUD_AWS_CREDENTIALS_SECRET_KEY))))
				.build();
	}
	@Bean
	public void createQueue() {
		Map<String, String> queueAttributes = new HashMap<String, String>();
		queueAttributes.put(FIFO_QUEUE, "true");
		queueAttributes.put(CONTENT_BASED_DEDUPLICATION, "true");
		CreateQueueRequest createFifoQueueRequest = new CreateQueueRequest(SPRING_BOOT_AWS_FIFO_QUEUE).withAttributes(queueAttributes);
		String fifoQueueUrl = amazonSQSAsync().createQueue(createFifoQueueRequest)
				.getQueueUrl();
		log.debug("SpringBoot AWS SQS FIFO URL :"+fifoQueueUrl);
	}
}
