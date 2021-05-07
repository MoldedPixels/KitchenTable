package com.project2.kitchentable.beans;

import java.util.List;

public class Kitchen{
	private int id;
	private int headUser;
	private int familyID;
	private List<Ingredient> shoppingList;
	private List<Ingredient> inventory;
	
	public Kitchen() {
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
		return "Ingredient [family=" + familyID + ", head=" + headUser + ", ID=" + id + "]";
	}
	public int getID() {
		return id;
	}
	public void setID(int newID) {
		this.id = newID;
	}

	public int getHeadUser() {
		return headUser;
	}

	public void setHeadUser(int headUser) {
		this.headUser = headUser;
	}

	public int getFamilyID() {
		return familyID;
	}

	public void setFamilyID(int familyID) {
		this.familyID = familyID;
	}

	public List<Ingredient> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<Ingredient> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public List<Ingredient> getInventory() {
		return inventory;
	}

	public void setInventory(List<Ingredient> inventory) {
		this.inventory = inventory;
	}
	
}
