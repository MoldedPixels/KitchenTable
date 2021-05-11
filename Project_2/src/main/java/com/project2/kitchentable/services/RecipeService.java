package com.project2.kitchentable.services;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.beans.Recipe;

import reactor.core.publisher.Mono;

public interface RecipeService {

	List<Ingredient> removeFood(List<Ingredient> list, UUID ingredient, Double amount);

	void cook();

	Mono<Recipe> getRecipeByID(UUID id);

	Mono<Recipe> updateRecipe(Recipe r);

	Mono<Recipe> addRecipe(Recipe r);

	List<Ingredient> getShoppingList(String recipeId) throws Exception;

	List<Ingredient> getRecipeIng(String recipe) throws JsonParseException, Exception;
}
