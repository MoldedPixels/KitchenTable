package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.ReactiveUserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	private ReactiveUserRepo userRepo;

	@Override
	public Mono<User> getUsersByName(String lname, String fname) {
		return userRepo.findByLastnameAndFirstname(lname,fname);
	}

	@Override
	public Mono<User> addUser(User u) {
		return userRepo.insert(u);
	}

	@Override
	public Mono<User> updateUser(User user) {
		log.trace(user);
		return this.userRepo.save(user);
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

