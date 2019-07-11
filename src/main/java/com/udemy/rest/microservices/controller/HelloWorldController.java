package com.udemy.rest.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController extends AbstractController{

	@GetMapping(path="/helloWorld")
	public String helloWorld() {
		return "Hello World.";
	}
}
