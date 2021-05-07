package com.project2.kitchentable.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.ReactiveUserRepo;

//Left off here, thats why there are errors.
@Service
public class UserServiceImpl implements UserService {
	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private ReactiveUserRepo userRepo;

	@Override
	public User getUser(String fname, String lname) {
		return ud.getUserByName(fname, lname);
	}

	@Override
	public boolean addUser(User u) {
		try {
			ud.addUser(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("User already exists " + u.getFirstName() + u.getLastName());
			return false;
		}
	}

	@Override
	public List<User> getUsers() {
		return ud.getUsers();
	}

	@Override
	public void updateUser(User u) {
		ud.updateUser(u);
	}

	@Override
	public User getUserByID(int userID) {
		return ud.getUserByID(userID);
	}
}
