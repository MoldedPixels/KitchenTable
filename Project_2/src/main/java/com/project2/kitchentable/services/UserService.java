package com.project2.kitchentable.services;

import java.util.UUID;

import com.project2.kitchentable.beans.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> getUsersByName(String fname, String lname);

	Mono<User> addUser(User u);

	Flux<User> getUsers();

	Mono<Void> removeUser(User u);

	Mono<User> updateUser(User user);

}

