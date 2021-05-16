package com.project2.kitchentable.controllers;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.aspects.Admin;
import com.project2.kitchentable.aspects.LoggedIn;
import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.RecipeService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	@Autowired
	private AuthController authorize;
	private static Logger log = LogManager.getLogger(RecipeController.class);

	@Admin
	@PostMapping("/add")
	public Mono<ResponseEntity<Recipe>> addRecipe(ServerWebExchange exchange, @RequestBody Recipe r) {
		try {
			log.trace("Adding recipe");
			r.setRecipeId(Uuids.timeBased());
			return recipeService.addRecipe(r).map(recipe -> ResponseEntity.status(201).body(recipe))
					.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(r)));
		} catch (Exception e) {
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}

	@LoggedIn
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<Recipe> getRecipes(ServerWebExchange exchange) {
		User u = authorize.UserAuth(exchange);
		if (u != null) {
			return recipeService.getRecipes();
		}
		exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
		return null;
	}

	@Admin
	@PutMapping("update/{recipeID}")
	public Mono<ResponseEntity<Recipe>> updateRecipe(ServerWebExchange exchange,
			@PathVariable("recipeID") String recipeID, @RequestBody Recipe r) {
		return recipeService.updateRecipe(r).map(recipe -> ResponseEntity.status(201).body(recipe))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(r)));
	}

	@DeleteMapping("remove/{recipeID}")
	public Mono<Void> removeRecipe(ServerWebExchange exchange, @PathVariable("recipeID") String recipeId) {

		return recipeService.removeRecipeById(UUID.fromString(recipeId));
	}

	@LoggedIn
	@GetMapping(value = "/{recipeName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<Recipe> getRecipeByName(ServerWebExchange exchange,
			@PathVariable("recipeName") String recipeName) {
		return recipeService.getRecipeByName(recipeName);

	}
	
	@LoggedIn
	@GetMapping(value = "/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Publisher<Recipe> getRecipeById(ServerWebExchange exchange, @PathVariable("recipeId") UUID recipeId) {
		return recipeService.getRecipeById(recipeId);
	}
}
