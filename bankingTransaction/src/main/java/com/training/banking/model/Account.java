package com.training.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "Account")
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "amount")
	private BigDecimal amount;

	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;

	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Account() {
		super();
	}

	public Account(BigDecimal amount, Customer customer, Bank bank) {
		super();
		this.amount = amount;
		this.customer = customer;
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", amount=" + amount + ", customer=" + customer + ", bank=" + bank
				+ "]";
	}

}
