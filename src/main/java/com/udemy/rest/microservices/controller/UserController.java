package com.udemy.rest.microservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.core.DummyInvocationUtils;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.udemy.rest.microservices.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa")
	public List<User> findAllWithJPA() {
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/jpa/{id}")
	public Resource<User> findUserJPABy(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found for id: " + id);
		}

		// HATEOAS
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(DummyInvocationUtils.methodOn(this.getClass()).findUserJPABy(id));
		resource.add(linkTo.withRel("findUserJPABy"));
		
		return resource;
	}

	@GetMapping()
	public List<User> findAll() {
		return userDao.findAll();
	}

	@PostMapping()
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "/{id}")
	public Resource<User> findBy(@PathVariable int id) {
		User user = userDao.findBy(id);
		if (user == null) {
			throw new UserNotFoundException("User not found for id: " + id);
		}

		// HATEOAS
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(DummyInvocationUtils.methodOn(this.getClass()).findBy(id));
		resource.add(linkTo.withRel("userById"));
		
		return resource;
	}

	@DeleteMapping(path = "/{id}")
	public Resource deleteBy(@PathVariable int id) {
		User deletedUser = userDao.deleteBy(id);
		if (deletedUser == null) {
			throw new UserNotFoundException("User not found for id: " + id);
		}

		// HATEOAS
		Resource resource = new Resource<User>(deletedUser);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(DummyInvocationUtils.methodOn(this.getClass()).deleteBy(id));
		resource.add(linkTo.withRel("deleteUserById"));

		return resource;
	}
}
