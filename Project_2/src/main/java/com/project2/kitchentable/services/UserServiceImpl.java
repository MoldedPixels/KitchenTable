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
	public Flux<User> getUser(String firstname, String lastname) {
		Flux<User> users = userRepo.findAll();
		Flux<User> user = users.filter(user1 -> user1.getLastname().equals(lastname) && user1.getFirstname().equals(firstname));
		
		return user;
	}
	
	@Override
	public Mono<User> addUser(User u) {
		return userRepo.insert(u);
	}
	@Override
	public Mono<User> updateUser(User u) {
		return userRepo.save(u);
	}
	@Override
	public Flux<User> getUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public Mono<Void> removeUser(User u){
		return userRepo.delete(u);
	}
}
