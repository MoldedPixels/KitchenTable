package com.project2.kitchentable.controllers;

import java.time.Duration;
import java.util.UUID;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setTokenServicer(JWTParser parser) {
		this.tokenService = parser;
	}
	
	@GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<User> getUsers(ServerWebExchange exchange) {
		
		return userService.getUsers();
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
		
		return userService.getUsersByName(u.getLastname(), u.getFirstname());
	}
	
	@DeleteMapping("login")
	public ResponseEntity<Void> logout(ServerWebExchange exchange) {
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("users/{userID}")
	public Mono<User> updateUser(@PathVariable("userID") String userID, @RequestBody User u){
		
		return userService.updateUser(u);
	}
	
}
