package com.project2.kitchentable.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
	public void removeFood(List<Ingredient> list, UUID ingredient, Double amount) {
		
		
	}
	@Override
	public void cook() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingredient> getShoppingList(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		List<Ingredient> list = new ArrayList<Ingredient>();
		
		try {
			list = (List<Ingredient>) userKitchen.subscribe(Kitchen::getShoppingList);
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
		List<Ingredient> list = new ArrayList<Ingredient>();
		
		try {
			list = (List<Ingredient>) userKitchen.subscribe(Kitchen::getInventory);
		}catch(Exception e) {
			log.warn(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
			return null;
		}
		
		return list;
	}

}
