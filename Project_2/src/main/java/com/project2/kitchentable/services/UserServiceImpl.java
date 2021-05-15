package com.project2.kitchentable.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.ReactiveRecipeRepo;
import com.project2.kitchentable.data.ReactiveUserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
	@Autowired
	private ReactiveUserRepo userRepo;
	@Autowired
	private ReactiveRecipeRepo recipeRepo;

	@Override
	public Mono<User> getUsersByName(String lname, String fname) {
		return userRepo.findByLastnameAndFirstname(lname, fname);
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
	public Mono<Void> removeUser(UUID id) {
		return userRepo.deleteByUserID(id);
	}

	@Override
	public Mono<User> getUserByID(UUID userID) {
		log.trace("Attempting to locate the user with uuid: " + userID);
		return userRepo.findByUserID(userID);
	}

	@Override
	public Mono<List<Recipe>> getFavorites(UUID userid) {
		log.debug("list of favorites incoming...");
		// Get user for the list owner
		return userRepo.findByUserID(userid).flatMap(user -> {
			// Return a flux of recipes as collected mono<list>
			if (user.getFavorites() == null) {
				List<Recipe> defaultList = new ArrayList<>();
				defaultList.add(new Recipe());
				return Mono.just(defaultList);
			} else {
				return Flux.fromIterable(user.getFavorites()).flatMap(recipeid -> {
					// Transform list of favorite ids to the actual associated recipes
					return recipeRepo.findByRecipeId(recipeid);
				}).collectList();
			}
		});
	}

	@Override
	public Mono<ServerResponse> updateFavorites(UUID userid, UUID recipeid) {
		// Retrieve user from database
		User u = userRepo.findByUserID(userid).block();
		// Retrieve user's list of favorites
		List<UUID> list = u.getFavorites();
		// Add new recipe to the list by recipe ID
		list.add(recipeid);
		// Reset the new favorites list for the user
		u.setFavorites(list);
		// Save user back to repo
		userRepo.save(u);
		return null;
	}

	public Mono<User> setKitchenNull(User u) {
		u.setKitchenID(null);
		return userRepo.save(u);
	}
}