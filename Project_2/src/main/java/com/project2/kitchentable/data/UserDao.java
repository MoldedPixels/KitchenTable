package com.project2.kitchentable.data;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.project2.kitchentable.beans.User;

@Repository
public interface UserDao extends CassandraRepository<User, String> {
	List<User> getUsers();
	User getUserByName(String fname, String lname);
	void addUser(User u) throws Exception;
	void updateUser(User u);
	User getUserByID(int userID);
}
