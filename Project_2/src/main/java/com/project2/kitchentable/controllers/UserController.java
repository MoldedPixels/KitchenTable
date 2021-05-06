package com.project2.kitchentable.controllers;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.UserService;

@RestController
public class UserController {
	private static UserService us;
	
	@Autowired
	public void setUserService(UserService us) {
		this.us = us;
	}
	
//	@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Publisher<User> getUsers(ServiceWebExchange exchange) {
//	
//	}
	
	@PostMapping("users")
	public ResponseEntity<User> registerUser(@RequestBody User u) {
		us.addUser(u);
		return ResponseEntity.status(201).body(u);
	}
}
