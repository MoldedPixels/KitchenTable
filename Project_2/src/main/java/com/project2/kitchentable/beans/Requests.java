package com.project2.kitchentable.beans;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Table;

public class Requests implements Serializable{
	private static final long serialVersionUID = 5876575538166852444L;

	private int requestId;
	private int recipeId;
	private String cuisine;
	private String ingredients;
	private String name;
	private double rating;
	private String body;
}
