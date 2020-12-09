package com.springworld.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/triggerDocker")
public class DockerController {

	 @GetMapping("/welcome")
	public String getValue() {
		return "Welcome to Spring world Docker"; 
		
	}
}
