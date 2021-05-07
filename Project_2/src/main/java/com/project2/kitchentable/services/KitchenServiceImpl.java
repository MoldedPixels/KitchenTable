package com.project2.kitchentable.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.data.ReactiveKitchenRepo;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class KitchenServiceImpl implements KitchenService {
	private static Logger log = LogManager.getLogger(KitchenServiceImpl.class);
	
	@Autowired
	private ReactiveKitchenRepo kitchenRepo;
	
	@Override
	public Mono<Kitchen> addKitchen(Kitchen k){
		return kitchenRepo.insert(k);
	}
	
	public Flux<Kitchen> getKitchens() {
		return kitchenRepo.findAll();
	}
	
	public Mono<Kitchen> updateKitchen(Kitchen k){
		return kitchenRepo.save(k);
	}
	
	
	@Override
	public void removeFood() {
		// Have to return the map of ingredients and then edit and save
		
	}
	@Override
	public void cook() {
		// Isn't this still just removing food based on a recipe? 
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> getShoppingList(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		Map<Ingredient, Double> ingredientMap;
		List<Ingredient> list = new ArrayList<Ingredient>();
		
		try {
			ingredientMap = (Map<Ingredient, Double>) userKitchen.subscribe(Kitchen::getShoppingMap);
			list = ingredientMap.keySet().stream().collect(Collectors.toList());
		}catch(Exception e) {
			log.warn(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
			return null;
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> getKitchenInv(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		Map<Ingredient, Double> ingredientMap;
		List<Ingredient> list = new ArrayList<Ingredient>();
		
		try {
			ingredientMap = (Map<Ingredient, Double>) userKitchen.subscribe(Kitchen::getInventoryMap);
			list = ingredientMap.keySet().stream().collect(Collectors.toList());
		}catch(Exception e) {
			log.warn(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
			return null;
		}
		
		return list;
	}


	
}
