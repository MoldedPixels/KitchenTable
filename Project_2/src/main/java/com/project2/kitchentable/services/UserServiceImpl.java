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
		return null;
	}
	
	@Override
	public Mono<User> addUser(User u) {
		return userRepo.insert(u);
	}
	
	@Override
	public Mono<User> updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Flux<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Mono<User> getUserByID(UUID userID) {
		return userRepo.findById(userID.toString());
	}
}
