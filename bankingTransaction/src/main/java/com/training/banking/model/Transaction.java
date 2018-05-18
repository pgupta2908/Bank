package com.training.banking.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Transaction")
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "accountId")
	private Integer accountId;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "transaction_type")
	private String transactionType;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Transaction() {
		super();
	}

	public Transaction(Integer transactionId, Integer customerId, Integer accountId, BigDecimal amount,
			String transactionType) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.accountId = accountId;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", customerId=" + customerId + ", accountId=" + accountId
				+ ", amount=" + amount + ", transactionType=" + transactionType + "]";
	}

	/*
	 * @ManyToOne(targetEntity = Customer.class) private Customer customer;
	 * 
	 * @ManyToOne(targetEntity = Account.class) private Account account;
	 */

}
