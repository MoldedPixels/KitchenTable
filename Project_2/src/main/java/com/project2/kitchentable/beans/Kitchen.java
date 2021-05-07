package com.project2.kitchentable.beans;

import java.io.Serializable;
import java.util.Map;

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
	private int id;
	@PrimaryKeyColumn(
			name="headuser",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private int headUser;
	@Column
	private int familyID;
	@Column
	private Map<Ingredient, Double> shoppingMap;
	@Column
	private Map<Ingredient, Double> inventoryMap;
	
	public Kitchen() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Map<Ingredient, Double> getShoppingMap() {
		return shoppingMap;
	}

	public void setShoppingMap(Map<Ingredient, Double> shoppingMap) {
		this.shoppingMap = shoppingMap;
	}

	public Map<Ingredient, Double> getInventoryMap() {
		return inventoryMap;
	}

	public void setInventoryMap(Map<Ingredient, Double> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + familyID;
		result = prime * result + headUser;
		result = prime * result + id;
		result = prime * result + ((inventoryMap == null) ? 0 : inventoryMap.hashCode());
		result = prime * result + ((shoppingMap == null) ? 0 : shoppingMap.hashCode());
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
		if (familyID != other.familyID)
			return false;
		if (headUser != other.headUser)
			return false;
		if (id != other.id)
			return false;
		if (inventoryMap == null) {
			if (other.inventoryMap != null)
				return false;
		} else if (!inventoryMap.equals(other.inventoryMap))
			return false;
		if (shoppingMap == null) {
			if (other.shoppingMap != null)
				return false;
		} else if (!shoppingMap.equals(other.shoppingMap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kitchen [id=" + id + ", headUser=" + headUser + ", familyID=" + familyID + ", shoppingMap="
				+ shoppingMap + ", inventoryMap=" + inventoryMap + "]";
	}


	
}
