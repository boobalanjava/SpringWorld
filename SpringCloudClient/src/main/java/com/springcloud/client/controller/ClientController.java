package com.springcloud.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
	
	@Autowired Environment env;
	
	
	@GetMapping("getConfigValue/{key}")
	public String getConfigValue(@PathVariable String key) {
		
		System.out.println(":::::: GetConfig Value :::::::::"+env.getProperty(key));
		return key +" : "+env.getProperty(key);  
		
	}
	

}
