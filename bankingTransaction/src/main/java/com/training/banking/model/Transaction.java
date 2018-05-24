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
@Table(name = "Transaction")
@Entity
public class Transaction extends BaseEntity {

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

	/**
	 * @return
	 */
	public Integer getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Transaction() {
		super();
	}

	/**
	 * @param transactionId
	 * @param customerId
	 * @param accountId
	 * @param amount
	 * @param transactionType
	 */
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

}
