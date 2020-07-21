package com.spring.aws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@Service
public class AWSSNSService {

	private static final String AWS_ARN = "infra.aws.arn";
	@Autowired AmazonSNSClient awsSNSClient;
	@Autowired Environment env;
	
	public void subscribe(String email) {
		SubscribeRequest request = new SubscribeRequest(env.getProperty(AWS_ARN),"email",email);
		awsSNSClient.subscribe(request);
	}

	public void publish() {
		PublishRequest request = new PublishRequest(env.getProperty(AWS_ARN),bodyMessage(),"Welcome To SpringWord");
		awsSNSClient.publish(request);
		
	}

	private String bodyMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dear Raja,");
		builder.append(System.lineSeparator());
		builder.append("Welcome to Java Spring world !");
		builder.append(System.lineSeparator());
		builder.append("Thanks for Subscribtion! Will keep post the topic of advance in Spring.");
		builder.append(System.lineSeparator());
		builder.append("Thanks");
		builder.append(System.lineSeparator());
		builder.append("SpringWorld");
		return builder.toString();
	}

}
