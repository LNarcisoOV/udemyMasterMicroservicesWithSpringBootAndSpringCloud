package com.udemy.rest.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.rest.microservices.dao.UserDao;
import com.udemy.rest.microservices.model.User;

@RestController
@RequestMapping(value="/users")
public class UserController{
	
	@Autowired
	private UserDao userDao;

	@GetMapping(path="/")
	public List<User> findAll(){
		return userDao.findAll();
	}
	
	@PostMapping(path="/")
	public void save(@PathVariable User user){
		userDao.save(user);
	}
	
	@GetMapping(path="/{id}")
	public User findBy(@PathVariable int id){
		return userDao.findBy(id);
	}
}
