package com.project2.kitchentable.services;

import java.util.UUID;

import com.project2.kitchentable.beans.Ingredient;
import reactor.core.publisher.Mono;

public interface IngredientService {


	Mono<Ingredient> getIngredientByID(UUID id);

	Mono<Ingredient> updateIngredient(Ingredient k);

	Mono<Ingredient> addIngredient(Ingredient i);

}
