package com.udemy.rest.microservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.rest.microservices.model.HelloWorldBean;

@RestController
public class HelloWorldController{
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path="/helloWorld")
	public String helloWorld() {
		return "Hello World.";
	}
	
	@GetMapping(path="/helloWorldBean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World.");
	}
	
	@GetMapping(path="/helloWorldBean/{name}")
	public HelloWorldBean helloWorldBeanWithName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s.", name));
	}
	
	@GetMapping(path="/goodMorningInternationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
