package com.project2.kitchentable.data;

import java.util.List;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.User;

public interface KitchenDao {
	List<User> getUsers();
	User getUserByName(String fname, String lname);
	void addUser(User u) throws Exception;
	void updateUser(User u);
	User getUserByID(int userID);
	List<Ingredient> getShoppingList();
}
