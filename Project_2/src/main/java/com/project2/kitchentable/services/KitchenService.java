package com.project2.kitchentable.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;

import reactor.core.publisher.Mono;

public interface KitchenService {

	List<Ingredient> removeFood(List<Ingredient> list, UUID ingredient, Double amount);

	void cook();

	Mono<Kitchen> getKitchenByID(UUID id);

	Mono<Kitchen> updateKitchen(Kitchen k);

	Mono<Kitchen> addKitchen(Kitchen k);

	Map<UUID, Double> getShoppingList(String kitchenId) throws Exception;

	Map<UUID, Double> getKitchenInv(String kitchen) throws JsonParseException, Exception;

	Mono<Ingredient> addIngredient(Ingredient i);
}
