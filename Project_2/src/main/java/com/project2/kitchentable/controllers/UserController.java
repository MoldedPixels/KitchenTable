package com.project2.kitchentable.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.UserService;
import com.project2.kitchentable.services.UserServiceImpl;
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
	
	
}
