package com.project2.kitchentable.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.services.KitchenService;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {

	private KitchenService kitchenService;

	@Autowired
	public void setKitchenService(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping(value = "/kitchen/removeFood")
	public void removeFood(@RequestParam(name = "list", required = false) String listname,
			@RequestParam(name = "kitchen", required = false) UUID kID,
			@RequestParam(name = "ingredient", required = false) UUID iID,
			@RequestParam(name = "amount", required = false) Double amt) throws Exception {
		List<Ingredient> list = null;
		if (listname.equals("shopping")) {
			list = kitchenService.getShoppingList(kID.toString());
		} else if (listname.equals("inventory")) {
			list = kitchenService.getKitchenInv(kID.toString());
		} else {
			return;
		}
		kitchenService.removeFood(list, iID, amt);
	}

}
