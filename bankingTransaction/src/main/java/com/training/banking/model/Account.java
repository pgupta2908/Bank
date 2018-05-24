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
@Table(name = "Account")
@Entity
public class Account extends BaseEntity{

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

	/**
	 * @return
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public Account() {
		super();
	}

	/**
	 * @param amount
	 * @param customer
	 * @param bank
	 */
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
