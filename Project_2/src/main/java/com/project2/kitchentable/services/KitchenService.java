package com.project2.kitchentable.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.project2.kitchentable.beans.Ingredient;

public interface KitchenService {

	void removeFood();
	
	void cook();

	List<Ingredient> getShoppingList(int kitchen) throws Exception;

	List<Ingredient> getKitchenInv(int kitchen) throws JsonParseException, Exception;
}
