package com.spring.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.spring.aws.service.AWSSNSService;

@RestController
public class AWSSNSController {
	
	@Autowired AWSSNSService service;
	
	@GetMapping("/snsSubscribeInAWS/{email}")
	public String subscribeToAWSService(@PathVariable String email) {
		service.subscribe(email);
		return "Confirmation mail have sent to :"+email;
		
	}
	@GetMapping("/sendNotification")
	public String sendNotification() {
		service.publish();
		return "Message sent successfully";
		
	}

}
