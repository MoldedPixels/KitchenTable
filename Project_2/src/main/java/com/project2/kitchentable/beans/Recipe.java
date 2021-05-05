package com.project2.kitchentable.beans;

import java.util.List;

public class Recipe{
	private int id;
	private int cuisine;
	private String name;
	private List<Ingredient> ingredients;
	private double rating;
	
	public Recipe() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		return true;
	}
	@Override
	public String toString() {
		return "Recipe [name=" + name + ", cuisine=" + cuisine + ", ID=" + id + "]";
	}
	public int getID() {
		return id;
	}
	public void setID(int newID) {
		this.id = newID;
	}

	public int getCuisine() {
		return cuisine;
	}

	public void setCuisine(int cuisine) {
		this.cuisine = cuisine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	
}
