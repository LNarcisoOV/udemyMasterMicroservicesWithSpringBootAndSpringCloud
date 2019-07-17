package com.udemy.rest.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.rest.microservices.model.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/helloWorld")
	public String helloWorld() {
		return "Hello World.";
	}

	@GetMapping(path = "/helloWorldBean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World.");
	}

	@GetMapping(path = "/helloWorldBean/{name}")
	public HelloWorldBean helloWorldBeanWithName(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s.", name));
	}

	@GetMapping(path = "/goodMorningInternationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
