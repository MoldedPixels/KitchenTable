package com.project2.kitchentable.beans;

import java.io.Serializable;
import java.util.Map;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("recipe")
public class Recipe implements Serializable {
	private static final long serialVersionUID = -5825156404073366871L;
	@Column
	private int id;
	@PrimaryKeyColumn(
			name="cuisine",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED)
	private int cuisine;
	@Column
	private String name;
	@Column
	private Map<Ingredient, Double> ingredientsMap;
	@PrimaryKeyColumn(
			name="rating",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED,
			ordering=Ordering.DESCENDING)
	private double rating;
	
	public Recipe() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Map<Ingredient, Double> getIngredientsMap() {
		return ingredientsMap;
	}

	public void setIngredientsMap(Map<Ingredient, Double> ingredientsMap) {
		this.ingredientsMap = ingredientsMap;
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
		result = prime * result + id;
		result = prime * result + ((ingredientsMap == null) ? 0 : ingredientsMap.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (id != other.id)
			return false;
		if (ingredientsMap == null) {
			if (other.ingredientsMap != null)
				return false;
		} else if (!ingredientsMap.equals(other.ingredientsMap))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", cuisine=" + cuisine + ", name=" + name + ", ingredientsMap=" + ingredientsMap
				+ ", rating=" + rating + "]";
	}


	
}
