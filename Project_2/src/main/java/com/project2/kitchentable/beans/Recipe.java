package com.project2.kitchentable.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("recipe")
public class Recipe implements Serializable {
	private static final long serialVersionUID = -5825156404073366871L;
	@PrimaryKeyColumn(
			name="recipeid",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED)
	private UUID recipeID;
	@PrimaryKeyColumn(
			name="cuisine",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private int cuisine;
	@Column
	private String name;
	@Column
	private Map<UUID, Double> ingredients;
	@Column
	private double rating;
	
	public Recipe() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public Recipe(UUID id, int cuisine, String name, Object[] ingredients, double rating) {
		this.recipeID = id;
		this.cuisine = cuisine;
		this.name = name;
		this.ingredients = ArrayUtils.toMap(ingredients);
		this.rating = rating;
	}

	public int getCuisine() {
		return cuisine;
	}

	public void setCuisine(int cuisine) {
		this.cuisine = cuisine;
	}

	public UUID getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(UUID recipeID) {
		this.recipeID = recipeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<UUID, Double> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<UUID, Double> ingredients) {
		this.ingredients = ingredients;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cuisine;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((recipeID == null) ? 0 : recipeID.hashCode());
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
		Recipe other = (Recipe) obj;
		if (cuisine != other.cuisine)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		if (recipeID == null) {
			if (other.recipeID != null)
				return false;
		} else if (!recipeID.equals(other.recipeID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe [recipeID=" + recipeID + ", cuisine=" + cuisine + ", name=" + name + ", ingredients="
				+ ingredients + ", rating=" + rating + "]";
	}

	
}
