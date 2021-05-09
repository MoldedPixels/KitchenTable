package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.ReactiveUserRepo;

@Service
public class UserServiceImpl implements UserService {
	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	private ReactiveUserRepo userRepo;
	
	@Override
	public Mono<User> getUser(String fname, String lname) {
		// TODO Auto-generated method stub
		/* Unlikely we can search like this, suggestion below
		 * Use userRepo.findAll()
		 * Iterate through the results, compare each row's name data to our parameters
		 * if you get a match, return that data as a Mono<User>
		 * 
		 * Might be worth implementing something like this in ReactiveUserRepo.java
		 * -AG
		 */
		return null;
	}
	
	@Override
	public Mono<User> addUser(User u) {
		log.trace("Attempting to add user: " + u.toString());
		return userRepo.insert(u);
	}
	
	@Override
	public Mono<User> updateUser(User u) {
		log.trace("Attempting to update user: " + u.toString());
		return userRepo.save(u);
	}
	
	@Override
	public Flux<User> getUsers() {
		log.trace("Attempting to find all users: ");
		return userRepo.findAll();
	}
	
	@Override
	public Mono<User> getUserByID(UUID userID) {
		log.trace("Attempting to locate the user with uuid: " + userID);
		return userRepo.findById(userID.toString());
	}
}
