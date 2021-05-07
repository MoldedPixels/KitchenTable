package com.project2.kitchentable.beans;
import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("ingredient")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 468730651941425795L;
	@PrimaryKeyColumn(
			name="id",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED)
	private int id;
	@PrimaryKeyColumn(
			name="name",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private String name;
	@Column
	private double amount;
	
	public Ingredient() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Ingredient other = (Ingredient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;	
		if (id != other.id)
			return false;
		if (amount != other.amount)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ingredient [name=" + name + ", amount=" + amount + ", ID=" + id + "]";
	}
	public int getID() {
		return id;
	}
	public void setID(int newID) {
		this.id = newID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double d) {
		this.amount = d;
	}
	public String getName() {
		return name;
	}
	public void setName(String fname) {
		this.name = fname;
	}
}
