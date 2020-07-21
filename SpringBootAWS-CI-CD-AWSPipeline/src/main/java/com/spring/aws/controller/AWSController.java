package com.spring.aws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadaws")
public class AWSController {
	@GetMapping("/getValueFromAWS")
	public String getValue() {
		return "Welcome to AWS"; 
		
	}
	@GetMapping("/getValueFromAWS1")
	public String getValues() {
		return "Im a secound service"; 
		
	}
	@GetMapping("/getValueFrom8")
	public String getValues2() {
		return "Im a third service"; 
		
	}
	@GetMapping("/getValueFrom9")
	public String getValues7() {
		return "Im a 10th service"; 
		
	}
}
