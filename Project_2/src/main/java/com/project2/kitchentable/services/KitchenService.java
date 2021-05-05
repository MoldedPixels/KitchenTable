package com.project2.kitchentable.services;

import java.util.List;

import com.project2.kitchentable.beans.Ingredient;

public interface KitchenService {

	void removeFood();
	
	void cook();

	List<Ingredient> getShoppingList();
}
