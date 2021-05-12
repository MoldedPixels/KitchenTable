package com.project2.kitchentable.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.data.ReactiveIngredientRepo;
import com.project2.kitchentable.data.ReactiveKitchenRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KitchenServiceImpl implements KitchenService {
	private static Logger log = LogManager.getLogger(KitchenServiceImpl.class);

	@Autowired
	private ReactiveKitchenRepo kitchenRepo;
	@Autowired
	private ReactiveIngredientRepo ingredientRepo;

	@Override
	public Mono<Kitchen> addKitchen(Kitchen k) {
		return kitchenRepo.save(k);
	}

	public Flux<Kitchen> getKitchens() {
		return kitchenRepo.findAll();
	}

	public Mono<Kitchen> updateKitchen(Kitchen k) {
		return kitchenRepo.save(k);
	}

	public Mono<Kitchen> getKitchenByID(UUID id) {
		return kitchenRepo.findById(id.toString());
	}

	public Mono<Void> removeKitchen(Kitchen k) {
		return kitchenRepo.delete(k);
	}

	@Override
	public Mono<Kitchen> removeFood(String listname, Kitchen k, UUID ingredient, Double amount) {
		Map<UUID, Double> list = null;
		if (listname.equals("shopping")) {
			list = k.getShoppingList();
		} else if (listname.equals("inventory")) {
			list = k.getInventory();
		} else {
		}
		for (UUID iID : list.keySet()) {
			if (iID.equals(ingredient)) {
				double newAmt = list.get(iID) - amount;
				if (newAmt > 0) {
					list.replace(iID, newAmt);
					log.trace("Successfully removed " + amount + "of " + ingredient);
				} else {
					log.trace("Failed to remove " + amount + "of " + ingredient
							+ ". The requested amount was too large.");
				}
			}

		}
		if (listname.equals("shopping")) {
			k.setShoppingList(list);
		} else if (listname.equals("inventory")) {
			k.setInventory(list);
		}
		return Mono.just(k);
	}

	@Override
	public Mono<Kitchen> addFood(String listname, Kitchen k, UUID ingredient, Double amt) {
		Map<UUID, Double> list = null;
		if (listname.equals("shopping")) {
			list = k.getShoppingList();
		} else if (listname.equals("inventory")) {
			list = k.getInventory();
		} else {
		}
		if (list.putIfAbsent(ingredient, amt) == null) {
			for (UUID iID : list.keySet()) {
				if (iID.equals(ingredient)) {
					double newAmt = list.get(iID) + amt;
					list.replace(iID, newAmt);
					log.trace("Successfully added " + amt + "to " + ingredient);
				}
			}
		}
		if (listname.equals("shopping")) {
			k.setShoppingList(list);
		} else if (listname.equals("inventory")) {
			k.setInventory(list);
		}

		return Mono.just(k);
	}

	@Override
	public void cook() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<UUID, Double> getShoppingList(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		Map<UUID, Double> list = new HashMap<UUID, Double>();

		try {
			list = (Map<UUID, Double>) userKitchen.subscribe(Kitchen::getShoppingList);
		} catch (Exception e) {
			log.warn(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
			return null;
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<UUID, Double> getKitchenInv(String kitchenId) throws Exception {
		Mono<Kitchen> userKitchen = kitchenRepo.findById(kitchenId);
		Map<UUID, Double> list = new HashMap<UUID, Double>();

		try {
			list = (Map<UUID, Double>) userKitchen.subscribe(Kitchen::getInventory);
		} catch (Exception e) {
			log.warn(e.getMessage());
			for (StackTraceElement st : e.getStackTrace())
				log.debug(st.toString());
			return null;
		}

		return list;
	}

}
