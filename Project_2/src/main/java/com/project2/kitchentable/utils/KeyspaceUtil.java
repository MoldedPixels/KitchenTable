package com.project2.kitchentable.utils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

public class KeyspaceUtil {
	private static CqlSession session = CassandraUtil.getInstance().getSession();

	public static void ingredientsTable() {
		StringBuilder ingredientsTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("ingredients (")
				.append("id_kitchen timeuuid,")
				.append("name text,")
				.append("measurement text,")
				.append("amount double,")
				.append("PRIMARY KEY (id_kitchen, name))");
		CassandraUtil.getInstance().getSession().execute(ingredientsTable.toString());
	}

	public static void recipesTable() {
		StringBuilder recipesTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("recipes (")
				.append("cuisine text,")
				.append("rating double,")
				.append("name text,")
				.append("ingredients text,")
				.append("servings int,")
				.append("PRIMARY KEY (cuisine, rating))");
		CassandraUtil.getInstance().getSession().execute(recipesTable.toString());
	}
	
	public static void kitchenTable() {
		StringBuilder kitchenTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("kitchen (")
				.append("id_family timeuuid,")
				.append("id_kitchen timeuuid,")
				.append("user_main text,")
				.append("list_shopping text,")
				.append("inventory int,")
				.append("PRIMARY KEY (id_family, user_main))");
		CassandraUtil.getInstance().getSession().execute(kitchenTable.toString());
	}
	
	public static void usersTable() {
		StringBuilder usersTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("employees (")
				.append("id_user timeuuid,")
				.append("id_kitchen timeuuid,")
				.append("name_first text,")
				.append("name_last text,")
				.append("usertype int,")
				.append("favorites list<text>,")
				.append("PRIMARY KEY (id_user, kitchenid, usertype))");
		CassandraUtil.getInstance().getSession().execute(usersTable.toString());
	}
	
	public static void reviewsTable() {
		StringBuilder reviewsTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("kitchen (")
				.append("id_review timeuuid,")
				.append("id_user timeuuid,")
				.append("id_recipe timeuuid,")
				.append("score double,")
				.append("body text,")
				.append("PRIMARY KEY (reviewid, id_user, score, recipeid))");
		CassandraUtil.getInstance().getSession().execute(reviewsTable.toString());
	}
	
	public static void familiesTable() {
		StringBuilder familiesTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("kitchen (")
				.append("id_family timeuuid,")
				.append("id_user_head timeuuid,")
				.append("list_users list<timeuuid>,")
				.append("PRIMARY KEY (id_family, id_user_head))");
		CassandraUtil.getInstance().getSession().execute(familiesTable.toString());
	}
	
	public static void notesTable() {
		StringBuilder notesTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("kitchen (")
				.append("id_review timeuuid,")
				.append("id_user timeuuid,")
				.append("id_recipe timeuuid,")
				.append("body text,")
				.append("PRIMARY KEY (reviewid, id_user, id_recipe))");
		CassandraUtil.getInstance().getSession().execute(notesTable.toString());
	}
	
	public static void requestsTable() {
		StringBuilder requestsTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
				.append("kitchen (")
				.append("id_request timeuuid,")
				.append("id_recipe timeuuid,")
				.append("cuisine text,")
				.append("ingredients text,")
				.append("name text,")
				.append("rating double,")
				.append("PRIMARY KEY (id_request, id_recipe))");
		CassandraUtil.getInstance().getSession().execute(requestsTable.toString());
	}

	public static void dropTable(String table) {
		String query = ("DROP TABLE IF EXISTS " + table + ";");
		session.execute(query);
	}

	public static void dbtest() {
		StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
				.append("KitchenTable with replication = {")
				.append("'class':'SimpleStrategy','replication_factor':1};");
		DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
		CqlSession.builder().withConfigLoader(loader).build().execute(sb.toString());
	}

}
