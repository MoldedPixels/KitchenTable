package com.project2.kitchentable.beans;

public class Ingredient{
	private int id;
	private String name;
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
