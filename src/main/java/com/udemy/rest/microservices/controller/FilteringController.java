package com.udemy.rest.microservices.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.rest.microservices.model.SomeBean;

@RestController
@RequestMapping(value="/filtering")
public class FilteringController {
	
	@GetMapping
	public SomeBean retrieveSomeBean(){
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping(value="/list")
	public List<SomeBean> retrieveListOfSomeBeans(){
		return Arrays.asList(new SomeBean("value11", "value12", "value13"),
				new SomeBean("value21", "value22", "value23"),
				new SomeBean("value31", "value32", "value33"));
	}
}
