package com.project2.kitchentable.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.type.UserDefinedType;
import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.Kitchen;
import com.project2.kitchentable.utils.CassandraUtil;

@Component
public class CreateCassObjects {
	@Autowired
	private CqlSession session;
	CassandraOperations template = new CassandraTemplate(session);
	private static final Logger log = LogManager.getLogger(CreateCassObjects.class);
	
	private static Kitchen newKitchen(String name, int age) {
	    return new Kitchen(UUID.randomUUID().toString(), name, age);
	  }

	public void createKitchenTable() {
		  Kitchen jonDoe = template.insert(newKitchen("Jon Doe", 40));
		  LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getId());
		  template.truncate(Kitchen.class);

	}
	
	public void createIngredientTable() {
		StringBuilder ingredientTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ingredients (")
				.append("id int, foodname text, amount double, primary key(id, foodname));");
		CassandraUtil.getInstance().getSession().execute(ingredientTable.toString());
	}
	
	public void createRecipeTable() {
		StringBuilder recipeTable = new StringBuilder("CREATE TABLE IF NOT EXISTS recipes (")
				.append("cuisine text, ingredients text, name text, rating double, primary key(cuisine, rating));");
		CassandraUtil.getInstance().getSession().execute(recipeTable.toString());
	}
	
	public void createUserTable() {
		StringBuilder userTable = new StringBuilder("CREATE TABLE IF NOT EXISTS users (")
				.append("userid int, kitchenid int, usertype int, firstname text, lastname text, primary key(userid, kitchenid, usertype));");
		CassandraUtil.getInstance().getSession().execute(userTable.toString());
	}
	
	public void createReviewsTable() {
		StringBuilder reviewsTable = new StringBuilder("CREATE TABLE IF NOT EXISTS reviews (")
				.append("reviewid int, userid int, body text, score double, recipeid int, primary key(recipeid, score));");
		CassandraUtil.getInstance().getSession().execute(reviewsTable.toString());
	}
	
	public void createFamilyTable() {
		StringBuilder familyTable = new StringBuilder("CREATE TABLE IF NOT EXISTS families (")
				.append("familyid int, members list<int>, headuser int, primary key(familyid, headuser));");
		CassandraUtil.getInstance().getSession().execute(familyTable.toString());
	}
	
	public void createNotesTable() {
		StringBuilder notesTable = new StringBuilder("CREATE TABLE IF NOT EXISTS notes (")
				.append("reviewid int, body text, userid int, recipeid int, primary key(reviewid, userid));");
		CassandraUtil.getInstance().getSession().execute(notesTable.toString());
	}
	
	
	
}
