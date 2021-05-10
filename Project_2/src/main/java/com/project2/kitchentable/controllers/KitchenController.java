package com.project2.kitchentable.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.services.KitchenService;
import com.revature.beans.Player;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {

	private KitchenService kitchenService;

	@Autowired
	public void setKitchenService(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@PostMapping("/new")
	public Mono<ResponseEntity<Kitchen>> addKitchen(@RequestBody Kitchen k) {
		System.out.println("Hello from register");
		return kitchenService.addKitchen(k).map(kitchen -> ResponseEntity.status(201).body(kitchen))
				.onErrorResume(error -> Mono.just(ResponseEntity.badRequest().body(k)));
	}

	/*
	 * @GetMapping(value = "/kitchen/removeFood") public void
	 * removeFood(@RequestParam(name = "list", required = false) String listname,
	 * 
	 * @RequestParam(name = "kitchen", required = false) UUID kID,
	 * 
	 * @RequestParam(name = "ingredient", required = false) UUID iID,
	 * 
	 * @RequestParam(name = "amount", required = false) Double amt) throws Exception
	 * { List<Ingredient> list = null; if (listname.equals("shopping")) { list =
	 * kitchenService.getShoppingList(kID.toString()); } else if
	 * (listname.equals("inventory")) { list =
	 * kitchenService.getKitchenInv(kID.toString()); } else { return; }
	 * kitchenService.removeFood(list, iID, amt); }
	 * 
	 * @GetMapping(value = "/kitchen/removeFood") public void
	 * removeFoodReactive(@RequestParam(name = "list", required = false) String
	 * listname,
	 * 
	 * @RequestParam(name = "kitchen", required = false) UUID kID,
	 * 
	 * @RequestParam(name = "ingredient", required = false) UUID iID,
	 * 
	 * @RequestParam(name = "amount", required = false) Double amt) throws Exception
	 * { List<Ingredient> list = null; if (listname.equals("shopping")) { list =
	 * kitchenService.getShoppingList(kID.toString()); } else if
	 * (listname.equals("inventory")) { list =
	 * kitchenService.getKitchenInv(kID.toString()); } else { return; }
	 * kitchenService.removeFood(list, iID, amt); }
	 */

}
