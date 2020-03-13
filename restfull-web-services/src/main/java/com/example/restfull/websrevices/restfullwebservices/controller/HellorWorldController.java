package com.example.restfull.websrevices.restfullwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfull.websrevices.restfullwebservices.model.HelloWorldModel;

@RestController
@RequestMapping(path="hello-world")
public class HellorWorldController {

	@GetMapping()
	public String helloWorld() {
		return "Hello Woorld";
	}
	
	@GetMapping(path="/bean")
	public HelloWorldModel helloWorldBean() {
		return new HelloWorldModel("Hello World!");
	}	
}
