package com.project2.kitchentable.services;

import java.util.UUID;

import com.project2.kitchentable.beans.Recipe;

import reactor.core.publisher.Flux;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.project2.kitchentable.beans.Ingredient;
import reactor.core.publisher.Mono;

public interface RecipeService {

	Mono<Recipe> addRecipe(Recipe r);

	Flux<Recipe> getRecipes();

	Mono<Recipe> updateRecipe(Recipe r);

	Mono<Recipe> getRecipeById(UUID id);

	Mono<Void> removeRecipe(Recipe r);

	List<Ingredient> removeFood(List<Ingredient> list, UUID ingredient, Double amount);

	void cook();

	Mono<Recipe> getRecipeByID(UUID id);

	List<Ingredient> getShoppingList(String recipeId) throws Exception;

	List<Ingredient> getRecipeIng(String recipe) throws JsonParseException, Exception;
}

