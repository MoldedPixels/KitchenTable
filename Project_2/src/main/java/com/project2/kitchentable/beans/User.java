package com.project2.kitchentable.beans;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user")
public class User implements Serializable {
	private static final long serialVersionUID = 9162925644169168189L;
	@PrimaryKeyColumn(
			name="id",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED)
	private int userID;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private int familyID;
	@PrimaryKeyColumn(
			name="kitchenid",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private int kitchenID;
	@PrimaryKeyColumn(
			name="usertype",
			ordinal=2,
			type=PrimaryKeyType.CLUSTERED)
	private int userTypeID;
	
	public User() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + userID;
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
		User other = (User) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (userID != other.userID)
			return false;
		if (userTypeID != other.userTypeID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Player [name=" + firstname + lastname + ", type" + userTypeID + ", userID=" + userID + "]";
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getKitchenID() {
		return kitchenID;
	}
	public void setKitchenID(int kitchenID) {
		this.kitchenID = kitchenID;
	}
	public int getUserTypeID() {
		return userTypeID;
	}
	public void setUserTypeID(int userTypeID) {
		this.userTypeID = userTypeID;
	}
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String fname) {
		this.firstname = fname;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lname) {
		this.lastname = lname;
	}

	public int getFamilyID() {
		return familyID;
	}

	public void setFamilyID(int familyID) {
		this.familyID = familyID;
	}
}
