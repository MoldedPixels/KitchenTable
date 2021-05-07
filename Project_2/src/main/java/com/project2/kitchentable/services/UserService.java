package com.project2.kitchentable.services;

import java.util.UUID;

import com.project2.kitchentable.beans.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> getUser(String fname, String lname);
	
	Mono<User> addUser(User u);
	
	Mono<User> updateUser(User u);
	
	Flux<User> getUsers();

	Mono<User> getUserByID(UUID userID);
}
