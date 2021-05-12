package com.project2.kitchentable.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	
	public Mono<Kitchen> getKitchenById(UUID id){
		String kitchenId = id.toString();
		
		return kitchenRepo.findById(kitchenId);
	}
	
	public Mono<Void> removeKitchen(Kitchen k){
		return kitchenRepo.delete(k);
	}
	
	
	@Override
	public void removeFood(List<Ingredient> list, UUID ingredient, Double amount) {
		
		
	}
	@Override
	public void cook() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Double> getShoppingList(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		Map<String, Double> list = new HashMap<>();
		
		try {
			list = (Map<String, Double>) userKitchen.subscribe(Kitchen::getShoppingList);
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
	public Map<String, Double> getKitchenInv(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		Map<String, Double> list = new HashMap<>();
		
		try {
			list = (Map<String, Double>) userKitchen.subscribe(Kitchen::getInventory);
		}catch(Exception e) {
			log.warn(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
			return null;
		}
		
		return list;
	}

}
