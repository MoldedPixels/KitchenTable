package com.project2.kitchentable.beans;

import java.io.Serializable;
import java.util.UUID;

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
	private UUID userID;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private UUID familyID;
	@PrimaryKeyColumn(
			name="kitchenid",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private UUID kitchenID;
	@PrimaryKeyColumn(
			name="usertype",
			ordinal=2,
			type=PrimaryKeyType.CLUSTERED)
	private int userType;

	public User(UUID userID, String firstname, String lastname, UUID familyID, UUID kitchenID, int userType) {
		super();
		this.userID = userID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.familyID = familyID;
		this.kitchenID = kitchenID;
		this.userType = userType;
	}

	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public UUID getFamilyID() {
		return familyID;
	}

	public void setFamilyID(UUID familyID) {
		this.familyID = familyID;
	}

	public UUID getKitchenID() {
		return kitchenID;
	}

	public void setKitchenID(UUID kitchenID) {
		this.kitchenID = kitchenID;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((familyID == null) ? 0 : familyID.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((kitchenID == null) ? 0 : kitchenID.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + userType;
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
		if (familyID == null) {
			if (other.familyID != null)
				return false;
		} else if (!familyID.equals(other.familyID))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (kitchenID == null) {
			if (other.kitchenID != null)
				return false;
		} else if (!kitchenID.equals(other.kitchenID))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", firstname=" + firstname + ", lastname=" + lastname + ", familyID="
				+ familyID + ", kitchenID=" + kitchenID + ", userType=" + userType + "]";
	}

}
