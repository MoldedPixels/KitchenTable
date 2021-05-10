package com.project2.kitchentable.services;

import java.util.UUID;

import com.project2.kitchentable.beans.Recipe;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {

	Mono<Recipe> addRecipe(Recipe r);

	Flux<Recipe> getRecipes();

	Mono<Recipe> updateRecipe(Recipe r);

	Mono<Recipe> getRecipeById(UUID id);

	Mono<Void> removeRecipe(Recipe r);

}