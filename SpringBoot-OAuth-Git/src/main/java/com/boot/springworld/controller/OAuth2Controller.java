package com.boot.springworld.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/home")
public class OAuth2Controller {
	
	@GetMapping("/user")
	public String getAuthorized(Principal pricipal) {
		log.debug("::::::::::: User been Authorized using OAuth ::::::::");
		return "User - "+pricipal.getName()+" Authendicated successfully to access resource ";
		
	}

}
