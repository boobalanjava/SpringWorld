package com.springworld.ses.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springworld.ses.sender.SESMailSender;

@RestController
@RequestMapping
public class SESController {

	@Autowired SESMailSender sendMail;
	@GetMapping("/sendMail")
	public String sendMail() throws UnsupportedEncodingException, MessagingException{

		sendMail.sendMailSES();
		return "Success";  
	}
}

