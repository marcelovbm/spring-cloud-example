package com.example.restfull.websrevices.restfullwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfull.websrevices.restfullwebservices.bean.HelloWorldBean;

@RestController
public class HellorWorldController {

	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello Woorld";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}	
}
