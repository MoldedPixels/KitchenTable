package com.project2.kitchentable.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

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

	List<Ingredient> getShoppingList(String kitchenId) throws Exception;

	List<Ingredient> getKitchenInv(String kitchen) throws JsonParseException, Exception;
}
