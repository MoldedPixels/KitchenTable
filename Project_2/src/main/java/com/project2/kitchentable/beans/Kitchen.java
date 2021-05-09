package com.project2.kitchentable.beans;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("kitchen")
public class Kitchen implements Serializable{
	private static final long serialVersionUID = 9003969767524051063L;
	@PrimaryKeyColumn(
			name="id",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED)
	private UUID id;
	@PrimaryKeyColumn(
			name="headuser",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private int headUser;
	@Column
	private UUID familyID;
	@Column
	private String shoppingList;
	@Column
	private String inventory;
	
	public Kitchen() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getHeadUser() {
		return headUser;
	}

	public void setHeadUser(int headUser) {
		this.headUser = headUser;
	}

	public UUID getFamilyID() {
		return familyID;
	}

	public void setFamilyID(UUID familyID) {
		this.familyID = familyID;
	}

	public String getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(String shoppingList) {
		this.shoppingList = shoppingList;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((familyID == null) ? 0 : familyID.hashCode());
		result = prime * result + headUser;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inventory == null) ? 0 : inventory.hashCode());
		result = prime * result + ((shoppingList == null) ? 0 : shoppingList.hashCode());
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
		Kitchen other = (Kitchen) obj;
		if (familyID == null) {
			if (other.familyID != null)
				return false;
		} else if (!familyID.equals(other.familyID))
			return false;
		if (headUser != other.headUser)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		if (shoppingList == null) {
			if (other.shoppingList != null)
				return false;
		} else if (!shoppingList.equals(other.shoppingList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kitchen [id=" + id + ", headUser=" + headUser + ", familyID=" + familyID + ", shoppingList="
				+ shoppingList + ", inventory=" + inventory + "]";
	}
	
	
}
