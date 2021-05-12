package com.project2.kitchentable.services;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.data.ReactiveIngredientRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientServiceImpl implements IngredientService {
	private static Logger log = LogManager.getLogger(IngredientServiceImpl.class);
	
	@Autowired
	private ReactiveIngredientRepo ingredientRepo;
	
	@Override
	public Mono<Ingredient> addIngredient(Ingredient i){
		return ingredientRepo.insert(i);
	}
	
	@Override
	public Flux<Ingredient> getIngredients() {
		return ingredientRepo.findAll();
	}
	
	@Override
	public Mono<Ingredient> updateIngredient(Ingredient i){
		return ingredientRepo.save(i);
	}
	
	@Override
	public Mono<Ingredient> getIngredientById(UUID id){
		String ingredientId = id.toString();
		
		return ingredientRepo.findById(ingredientId);
	}
	
	@Override
	public Mono<Void> removeIngredient(Ingredient i){
		return ingredientRepo.delete(i);
	}
}
