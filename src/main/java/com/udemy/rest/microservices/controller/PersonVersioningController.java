package com.udemy.rest.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.rest.microservices.model.Name;
import com.udemy.rest.microservices.model.PersonV1;
import com.udemy.rest.microservices.model.PersonV2;

@RestController
@RequestMapping("/person")
public class PersonVersioningController {

	@GetMapping("/v1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/v2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/param", params = "version=1")
	public PersonV1 getParamV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/param", params = "version=2")
	public PersonV2 getParamV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(value = "/header", headers = "X-API-VERSION=1")
	public PersonV1 getHeaderV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/header", headers = "X-API-VERSION=2")
	public PersonV2 getHeaderV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}
