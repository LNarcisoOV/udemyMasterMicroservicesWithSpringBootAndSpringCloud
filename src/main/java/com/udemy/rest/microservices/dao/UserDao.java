package com.udemy.rest.microservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.udemy.rest.microservices.model.User;

@Component
public class UserDao {
	private static List<User> users = new ArrayList<User>();
	
	static{
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
		users.add(new User(4, "John", new Date()));
		users.add(new User(5, "Maria", new Date()));
		users.add(new User(6, "Silvia", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		users.add(user);
		return user;
	}
	
	public User findBy(int id){
		return users.stream().filter(u -> u.getId().equals(id)).findFirst().get();
	}
}
