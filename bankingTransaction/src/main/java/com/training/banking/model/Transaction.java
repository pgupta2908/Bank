package com.training.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "Transaction")
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "transaction_type")
	private String transactionType;

	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;

	@ManyToOne(targetEntity = Account.class)
	private Account account;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(BigDecimal amount, String transactionType, Customer customer,
			Account account) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;
		this.customer = customer;
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", transactionType="
				+ transactionType + ", customer=" + customer + ", account=" + account + "]";
	}
	

}
