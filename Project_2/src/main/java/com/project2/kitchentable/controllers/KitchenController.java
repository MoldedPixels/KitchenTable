package com.project2.kitchentable.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.services.KitchenService;
import com.project2.kitchentable.services.KitchenServiceImpl;

@RestController
@RequestMapping(value = "/kitchen")
public class KitchenController {
	
	private KitchenService kitchenService;
	
	@Autowired
	public void setKitchenService(KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}
	
	
	
	
}
