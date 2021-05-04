package com.project2.kitchentable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.project2.kitchentable.controllers.UserController;
import com.project2.kitchentable.utils.CassandraUtil;

import io.javalin.Javalin;

public class Driver {
	private static final Logger log = LogManager.getLogger(Driver.class);

	public static void main(String[] args) {
		// Trace the flow of an application
		log.trace("Begin the application");
		javalin();
		dbtest(); // created my keyspace
		log.trace("Checking for UserTable");
		userTable(); // created usertable
		log.trace("Checking for FormTable");
		formsTable(); //created formsTable
		log.trace("Checking for LogsTable");
		logsTable(); //created logsTable
		log.trace("Checking for AttachmentsTable");
		attachmentsTable(); //created attachmentsTable
		//log.trace("Adding to Roles");
		//addRoles();
		//log.trace("Adding to Status");
		//addStatus();
	}
	
	/*
	 * public static void formsTable() { StringBuilder sb = new
	 * StringBuilder("CREATE TABLE IF NOT EXISTS ").append("Forms (")
	 * .append("form_id int, assigned_to int, form_status int, date_submitted text, event_date text,"
	 * )
	 * .append("direct_sup int, dept_head int, requestor int, request_amount double,"
	 * )
	 * .append("ds_status int, ds_status_date text, dh_status int, dh_status_date text,"
	 * ) .append("bco_status int, bco_status_date text, last_updated_date text,")
	 * .append("last_updated_by int, grading_format int, event_type int, justification text,"
	 * )
	 * .append("request_date text, denial_reason text, denied_by int, grade_submitted boolean, primary key(form_id));"
	 * ); CassandraUtil.getInstance().getSession().execute(sb.toString()); }
	 */
	

	public static void dbtest() {

		StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append("Project_1 with replication = {")
				.append("'class':'SimpleStrategy','replication_factor':1};");
		DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
		CqlSession.builder().withConfigLoader(loader).build().execute(sb.toString());

	}

	private static void javalin() {
		Javalin app = Javalin.create().start(8080);

		// Map controller functions to specific methods and urls

		// User Controller
		app.get("/users", UserController::getUsers);
		app.put("/users/register", UserController::register);
		app.post("/users/login", UserController::login);
		app.delete("/users/logout", UserController::logout);
		app.put("/users/update/:uid", UserController::update);

		// Recipe Controller
		
		// Kitchen Controller
		
		// Family Controller
		
		// Review Controller
		
		// Notes Controller
		
		// File Controller

		CassandraUtil.getInstance();
	}
}
