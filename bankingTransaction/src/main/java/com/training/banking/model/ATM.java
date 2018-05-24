package com.training.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pratyush Gupta
 *
 */
@Table(name = "ATM")
@Entity
public class ATM extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "atm_id")
	private Integer atmId;

	@Column(name = "amount")
	private BigDecimal amount;

	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	/**
	 * @return
	 */
	public Integer getAtmId() {
		return atmId;
	}

	/**
	 * @param atmId
	 */
	public void setAtmId(Integer atmId) {
		this.atmId = atmId;
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

	/**
	 * @return
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public ATM() {
		super();
	}

	/**
	 * @param atmId
	 * @param amount
	 */
	public ATM(Integer atmId, BigDecimal amount) {
		super();
		this.atmId = atmId;
		this.amount = amount;
	}

	/**
	 * @param atmId
	 * @param amount
	 * @param bank
	 */
	public ATM(Integer atmId, BigDecimal amount, Bank bank) {
		super();
		this.atmId = atmId;
		this.amount = amount;
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "ATM [atmId=" + atmId + ", amount=" + amount + ", bank=" + bank + "]";
	}

}
