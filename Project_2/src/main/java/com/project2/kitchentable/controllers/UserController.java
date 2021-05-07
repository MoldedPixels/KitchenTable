package com.project2.kitchentable.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.UserService;
import com.project2.kitchentable.utils.JWTParser;

@RestController
public class UserController {
	private UserService userService;
	private JWTParser tokenService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setTokenServicer(JWTParser parser) {
		this.tokenService = parser;
	}
	
//	@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Publisher<User> getUsers(ServiceWebExchange exchange) {
//	
//	}
	
	@PostMapping("users")
	public ResponseEntity<User> registerUser(@RequestBody User u) {
		userService.addUser(u);
		return ResponseEntity.status(201).body(u);
	}

}
