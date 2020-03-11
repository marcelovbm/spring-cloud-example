package com.example.restfull.websrevices.restfullwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfull.websrevices.restfullwebservices.model.HelloWorldModel;

@RestController
public class HellorWorldController {

	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello Woorld";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldModel helloWorldBean() {
		return new HelloWorldModel("Hello World!");
	}	
}
