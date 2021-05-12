package com.project2.kitchentable.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.services.IngredientService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/ingredient")
public class IngredientController {

	private IngredientService ingredientService;
	// private RecipeService recipeService;

	@Autowired
	public void setIngredientService(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	// @Autowired
//	public void setRecipeService(RecipeService recipeService) {
//		this.recipeService = recipeService;
//	}

	@PostMapping("/new")
	public Mono<ResponseEntity<Ingredient>> addIngredient(@RequestBody Ingredient i) {
		System.out.println("Adding a new Ingredient");
		i.setId(Uuids.timeBased());

		return ingredientService.addIngredient(i).map(ingredient -> ResponseEntity.status(201).body(ingredient))
				.onErrorStop();
		// .onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(i)));
	}

}
