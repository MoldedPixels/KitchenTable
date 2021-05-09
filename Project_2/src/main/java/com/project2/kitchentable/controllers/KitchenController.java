package com.project2.kitchentable.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonParser;
import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.services.KitchenService;
import com.project2.kitchentable.utils.JSONUtil;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {

	private KitchenService kitchenService;
	private JSONUtil jsonutil = JSONUtil.getInstance();
	@Autowired
	public void setKitchenService(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@GetMapping(value = "/kitchen/removeFood")
	public void removeFood(@RequestParam(name = "list", required = false) String listname,
			@RequestParam(name = "kitchen", required = false) UUID kID,
			@RequestParam(name = "ingredient", required = false) UUID iID,
			@RequestParam(name = "amount", required = false) Double amt) throws Exception {
		Kitchen k = kitchenService.getKitchenByID(kID).block();
		
		List<Ingredient> list = null;
		if (listname.equals("shopping")) {
			list = jsonutil.readIngredients(k.getShoppingList());
			list = kitchenService.removeFood(list, iID, amt);
			String result = jsonutil.writeIngredients(list);
			k.setShoppingList(result);
		} else if (listname.equals("inventory")) {
			list = jsonutil.readIngredients(k.getInventory());
			list = kitchenService.removeFood(list, iID, amt);
			String result = jsonutil.writeIngredients(list);
			k.setInventory(result);
		} else {
			return;
		}
		kitchenService.updateKitchen(k);
	}

}
