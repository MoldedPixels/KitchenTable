package com.project2.kitchentable.beans;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("reviews")
public class Reviews implements Serializable {
	private static final long serialVersionUID = -7568030855642391178L;
	
	@PrimaryKeyColumn(
			name="reviewid",
			ordinal=0,
			type=PrimaryKeyType.PARTITIONED)
	private int reviewId;
	@PrimaryKeyColumn(
			name="userid",
			ordinal=1,
			type=PrimaryKeyType.CLUSTERED)
	private int userId;
	@PrimaryKeyColumn(
			name="recipeid",
			ordinal=2,
			type=PrimaryKeyType.CLUSTERED)
	private int recipeId;
	@Column
	private double score;
	@Column
	private String body;

	public Reviews() {
		super();
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + recipeId;
		result = prime * result + reviewId;
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userId;
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
		Reviews other = (Reviews) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (recipeId != other.recipeId)
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", userId=" + userId + ", recipeId=" + recipeId + ", score=" + score
				+ ", body=" + body + "]";
	}
	
}
