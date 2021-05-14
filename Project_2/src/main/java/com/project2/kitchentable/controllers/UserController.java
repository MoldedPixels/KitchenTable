package com.project2.kitchentable.controllers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.UserService;
import com.project2.kitchentable.utils.JWTParser;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	private UserService userService;
	private JWTParser tokenService;
	@Autowired
	private AuthController authorize;
	
	private static Logger log = LogManager.getLogger(UserController.class);
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setTokenServicer(JWTParser parser) {
		this.tokenService = parser;
	}
	
	@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<User> getUsers(ServerWebExchange exchange) {
		User u = authorize.UserAuth(exchange);
		if(u != null && u.getUserType() == 3) {
			return userService.getUsers();
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@PostMapping("users")
	public Mono<ResponseEntity<User>> registerUser(@RequestBody User u) {
		u.setUserID(Uuids.timeBased());
		if(u.getFamilyID() == null) {
			u.setFamilyID(Uuids.timeBased());
		}
		if(u.getKitchenID() == null) {
			u.setKitchenID(Uuids.timeBased());
		}
		return userService.addUser(u).map(user -> ResponseEntity.status(201).body(user))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(u)));
	}
	
	@PostMapping(value="login", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Publisher<User> login(ServerWebExchange exchange, @RequestBody User u) {
		return userService.getUsersByName(u.getLastname(), u.getFirstname()).delayElement(Duration.ofSeconds(2)).doOnNext(user -> {
			try {
				exchange.getResponse().addCookie(ResponseCookie.from("token", tokenService.makeToken(user)).httpOnly(true).build());
			}catch(Exception e) {
				exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		});
	}
	
	@DeleteMapping("login")
	public ResponseEntity<Void> logout(ServerWebExchange exchange) {
		try {
			exchange.getResponse().addCookie(ResponseCookie.from("token", null).httpOnly(true).build());
		}catch(Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("users/{userID}")
	public Mono<User> updateUser(ServerWebExchange exchange, @PathVariable("userID") String userID, @RequestBody User u){
		User user = authorize.UserAuth(exchange);
		
		if(user != null && user.getUserType() == 3) {
			return userService.updateUser(u);
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@PutMapping("users/{userID}/{kitchenID}")
	public Mono<User> updateUserKitchen(ServerWebExchange exchange, @PathVariable("userID") String userID, @PathVariable("kitchenID") String kitchenID, @RequestBody User u){
		User user = authorize.UserAuth(exchange);
		
		if(user != null && u.getUserType() == 2 && user.getKitchenID() == UUID.fromString(kitchenID)) {
			u.setKitchenID(UUID.fromString(kitchenID));
			return userService.updateUser(u);
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@DeleteMapping("users/{userID}/{kitchenID}")
	public Mono<User> removeUserKitchen(ServerWebExchange exchange, @PathVariable("userID") String userID, @PathVariable("kitchenID") String kitchenID){
		User user = authorize.UserAuth(exchange);
		User u = null;
		try {
			u = userService.getUserByID(UUID.fromString(userID)).block(Duration.of(1000, ChronoUnit.MILLIS));
		}catch(Exception e) {
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
		}
		if(user != null && u!= null) {
			if(u.getUserType() == 2 && user.getKitchenID() == UUID.fromString(kitchenID)) {
				u.setKitchenID(null);
				return userService.updateUser(u);
			}
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
	@DeleteMapping("users/{userID}")
	public Mono<Void> removeUser(ServerWebExchange exchange, @PathVariable("userID") String userID){
		User user = authorize.UserAuth(exchange);
		if(user != null && user.getUserType() == 3) {
			try {
				User u = userService.getUserByID(UUID.fromString(userID)).block(Duration.of(1000, ChronoUnit.MILLIS));
				userService.removeUser(u);
			}catch(Exception e) {
				for (StackTraceElement st : e.getStackTrace())
					log.debug(st.toString());
			}
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}
	
}
