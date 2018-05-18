package com.training.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "ATM")
@Entity
public class ATM {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "atm_id")
	private Integer atmId;

	@Column(name = "amount")
	private BigDecimal amount;

	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	public Integer getAtmId() {
		return atmId;
	}

	public void setAtmId(Integer atmId) {
		this.atmId = atmId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public ATM() {
		super();
	}

	public ATM(Integer atmId, BigDecimal amount) {
		super();
		this.atmId = atmId;
		this.amount = amount;
	}

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
