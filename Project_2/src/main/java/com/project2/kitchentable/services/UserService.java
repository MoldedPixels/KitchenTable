package com.project2.kitchentable.services;

import java.util.List;

import com.project2.kitchentable.beans.User;

public interface UserService {

	User getUser(String fname, String lname);
	
	boolean addUser(User u);
	
	void updateUser(User u);
	
	List<User> getUsers();

	User getUserByID(int userID);
}
