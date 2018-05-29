package com.training.banking.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Pratyush Gupta
 *
 */
@MappedSuperclass
public class BaseEntity {

	@Column(name = "user_id")
	private String userId;

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userId
	 */
	public BaseEntity(String userId) {
		super();
		this.userId = userId;
	}

	public BaseEntity() {
		super();
	}

	@Override
	public String toString() {
		return "BaseEntity [userId=" + userId + "]";
	}

}
