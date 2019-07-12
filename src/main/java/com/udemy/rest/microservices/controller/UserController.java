package com.udemy.rest.microservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.udemy.rest.microservices.dao.UserDao;
import com.udemy.rest.microservices.exception.UserNotFoundException;
import com.udemy.rest.microservices.model.User;

@RestController
@RequestMapping(value="/users")
public class UserController{
	
	@Autowired
	private UserDao userDao;

	@GetMapping()
	public List<User> findAll(){
		return userDao.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<User> save(@RequestBody User user){
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/{id}")
	public User findBy(@PathVariable int id){
		User user = userDao.findBy(id);
		if(user == null){
			throw new UserNotFoundException("User not found for id: "+ id);
		}
		return user;
	}
}
