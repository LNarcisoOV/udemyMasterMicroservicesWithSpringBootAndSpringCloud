package com.udemy.rest.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.rest.microservices.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
