package com.springworld.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springworld.graphql.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/springWorld")
public class GraphQLController {
	
	@Autowired
	GraphQLService service;
	
	@PostMapping("/query")
	public ResponseEntity<Object> queryGraphQL(@RequestBody String query) {
		ExecutionResult result=service.getGraphql().execute(query);
		return new ResponseEntity<>(result,HttpStatus.OK); 
	}

}
