package com.project2.kitchentable.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.aspects.Admin;
import com.project2.kitchentable.aspects.FamilyMember;
import com.project2.kitchentable.aspects.HeadUser;
import com.project2.kitchentable.aspects.LoggedIn;
import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.UserService;
import com.project2.kitchentable.utils.JWTParser;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	private UserService userService;
	private JWTParser tokenService;

	private static Logger log = LogManager.getLogger(UserController.class);

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTokenServicer(JWTParser parser) {
		this.tokenService = parser;
	}

	@Admin
	@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<User> getUsers(ServerWebExchange exchange) {
		return userService.getUsers();

	}

	@PostMapping("register")
	public Mono<ResponseEntity<User>> registerUser(@RequestBody User u) {
		u.setUserID(Uuids.timeBased());
		if (u.getFamilyID() == null) {
			u.setFamilyID(Uuids.timeBased());
		}
		if (u.getKitchenID() == null) {
			u.setKitchenID(Uuids.timeBased());
		}
		if (u.getFavorites() == null) {
			List<UUID> favorites = new ArrayList<>();
			u.setFavorites(favorites);
		}
		if (u.getCookedRecipes() == null) {
			List<UUID> cooked = new ArrayList<>();
			u.setCookedRecipes(cooked);
		}
		return userService.addUser(u).map(user -> ResponseEntity.status(201).body(user))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(u)));
	}

	@PostMapping(value = "login", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Publisher<User> login(ServerWebExchange exchange, @RequestParam(name = "userid") UUID userid) {
		return userService.getUserByID(userid).doOnNext(user -> {
			try {
				exchange.getResponse()
						.addCookie(ResponseCookie.from("token", tokenService.makeToken(user)).httpOnly(true).build());
			} catch (Exception e) {
				exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		});
	}

	@DeleteMapping("logout")
	public ResponseEntity<Void> logout(ServerWebExchange exchange) {
		try {
			exchange.getResponse().addCookie(ResponseCookie.from("token", "").httpOnly(true).build());
		} catch (Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.noContent().build();
	}

	@Admin
	@PutMapping("update/{userID}")
	public Mono<User> updateUser(ServerWebExchange exchange, @PathVariable("userID") String userID,
			@RequestBody User u) {
		return userService.updateUser(u);

	}

	// This should be part of updateUser.
	@SuppressWarnings("unlikely-arg-type")
	@PutMapping("users/{userID}/{kitchenID}")
	public Mono<User> updateUserKitchen(ServerWebExchange exchange, @PathVariable("userID") String userID,
			@PathVariable("kitchenID") String kitchenID, @RequestBody User u) {
		u.setKitchenID(UUID.fromString(kitchenID));
		return userService.updateUser(u);
	}

	@SuppressWarnings("unlikely-arg-type")
	@HeadUser
	@FamilyMember
	@DeleteMapping("removeFromKitchen/{userID}/{kitchenID}")
	public Mono<User> removeUserKitchen(ServerWebExchange exchange, @PathVariable("kitchenID") String kitchenID,
			@RequestBody User u) {
		return userService.setKitchenNull(u);
	}

	@Admin
	@DeleteMapping("remove/{userID}")
	public Mono<Void> removeUser(ServerWebExchange exchange, @PathVariable("userID") String userID) {
		return userService.removeUser(UUID.fromString(userID));
	}

	@LoggedIn
	@GetMapping("favorites")
	public Mono<List<Recipe>> getFavorites(@RequestParam(name = "userid") UUID userId) {
		log.debug("Gathering list of favorites..");
		return userService.getFavorites(userId);

	}

	@LoggedIn
	@PutMapping("favorites/update")
	public Mono<User> updateFavorites(@RequestParam(name = "userId") UUID userId,
			@RequestParam(name = "recipeId") UUID recipeId) {
		log.debug("Update list of favorites for user id: %s", userId);
		return userService.updateFavorites(userId, recipeId);
	}

}
