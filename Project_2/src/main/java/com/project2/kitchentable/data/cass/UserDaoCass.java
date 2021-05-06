package com.project2.kitchentable.data.cass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.UserDao;
import com.project2.kitchentable.factory.Log;
import com.project2.kitchentable.utils.CassandraUtil;

@Service
public class UserDaoCass implements UserDao{
	@Autowired
	private CqlSession session;
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		String query = "select * from users";
		ResultSet rs = session.execute(query);
		
		rs.forEach(data -> {
			User u = new User();
			u.setFirstName(data.getString("firstname"));
			u.setLastName(data.getString("lastname"));
			u.setUserID(data.getInt("user_id"));
			u.setFamilyID(data.getInt("familyid"));
			u.setKitchenID(data.getInt("kitchenid"));
			u.setUserTypeID(data.getInt("usertype"));
			
			users.add(u);
		});
		
		return users;
	}

	@Override
	public User getUserByName(String fname, String lname) {
		User u = null;
		String query = "Select * from users where firstname = ? AND lastname = ?;";
		BoundStatement bound = session.prepare(query).bind(fname, lname);
		ResultSet rs = session.execute(bound);
		Row data = rs.one();
		if(data != null) {
			u = new User();
			u.setFirstName(data.getString("firstname"));
			u.setLastName(data.getString("lastname"));
			u.setUserTypeID(data.getInt("usertype"));
			u.setUserID(data.getInt("userid"));
			u.setFamilyID(data.getInt("familyid"));
			u.setKitchenID(data.getInt("kitchenid"));
			u.setUserTypeID(data.getInt("usertype"));
		}
		return u;
	}

	@Override
	public void addUser(User u) throws Exception {
		String query = "Insert into users (firstname, lastname, kitchenid, usertype, userid) values (?,?,?,?,?); ";
		SimpleStatement s = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s).bind(u.getFirstName(), u.getLastName(), u.getKitchenID(), u.getUserTypeID(), u.getUserID());
		session.execute(bound);
	}

	@Override
	public void updateUser(User u) {
		String query = "update users set firstname = ?, lastname = ?, kitchenid = ?, usertype = ?, where userid = ?";
		SimpleStatement s = new SimpleStatementBuilder(query)
				.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s).bind(u.getFirstName(), u.getLastName(), u.getKitchenID(), u.getUserTypeID(), u.getUserID());
		session.execute(bound);
	}

	@Override
	public User getUserByID(int userID) {
		User u = null;
		String query = "Select * from users where userid = ?;";
		BoundStatement bound = session.prepare(query).bind(userID);
		ResultSet rs = session.execute(bound);
		Row data = rs.one();
		if(data != null) {
			u = new User();
			u.setFirstName(data.getString("firstname"));
			u.setLastName(data.getString("lastname"));
			u.setUserID(data.getInt("user_id"));
			u.setFamilyID(data.getInt("familyid"));
			u.setKitchenID(data.getInt("kitchenid"));
			u.setUserTypeID(data.getInt("usertype"));
		}
		return u;
	}

}
