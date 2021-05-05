package com.project2.kitchentable.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.UserDao;
import com.project2.kitchentable.factory.BeanFactory;
import com.project2.kitchentable.factory.Log;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// The service layer allows us to do more complicated actions that strict data access
// Even if you don't do anything more important, the reason we need a service layer
// is to loosely couple our business layer from our data layer
@Service
public class UserServiceImpl implements UserService {
	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
	private UserDao ud;
	
	
	
	@Override
	public Mono<User> getUser(String fname, String lname) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Mono<User> addUser(User u) {
		// TODO Auto-generated method stub
		return null;
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
	public Mono<User> getUserByID(int userID) {
		// TODO Auto-generated method stub
		return null;
	}
}
