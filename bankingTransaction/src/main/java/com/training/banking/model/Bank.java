package com.training.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pratyush Gupta
 *
 */
@Table(name = "Bank")
@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bank_id")
	private Integer bankId;

	@Column(name = "amount")
	private BigDecimal amount;

	/**
	 * @return
	 */
	public Integer getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 */
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Bank() {
		super();
	}

	/**
	 * @param amount
	 */
	public Bank(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @param bankId
	 * @param amount
	 */
	public Bank(Integer bankId, BigDecimal amount) {
		super();
		this.bankId = bankId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", amount=" + amount + "]";
	}

}
