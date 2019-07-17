package com.udemy.rest.microservices.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the User.")
public class User {
	private Integer id;

	@Size(min = 2, message="The name should have at least 2 characters.")
	@ApiModelProperty(notes="The Name should have at least 2 characters.")
	private String name;
	
	@Past(message="Birthdate should be in the past.")
	@ApiModelProperty(notes="Birth date should be in the past.")
	private Date birthDate;

	public User() {
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
