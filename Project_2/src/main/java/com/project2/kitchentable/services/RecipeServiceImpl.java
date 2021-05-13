package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.project2.kitchentable.beans.Recipe;
import com.project2.kitchentable.data.ReactiveRecipeRepo;
import org.springframework.stereotype.Service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RecipeServiceImpl implements RecipeService {
	private static Logger log = LogManager.getLogger(RecipeServiceImpl.class);

	@Autowired
	private ReactiveRecipeRepo recipeRepo;

	@Override
	public Mono<Recipe> addRecipe(Recipe r) {
		log.trace("adding recipe");
		return recipeRepo.insert(r);
	}

	@Override
	public Flux<Recipe> getRecipes() {
		return recipeRepo.findAll();
	}

	@Override
	public Mono<Recipe> updateRecipe(Recipe r) {
		return recipeRepo.save(r);
	}

	@Override
	public Mono<Void> removeRecipe(Recipe r) {
		return recipeRepo.delete(r);
	}

	@Override
	public Mono<Recipe> getRecipeByID(UUID id) {
		return recipeRepo.findById(id);
	}
}
