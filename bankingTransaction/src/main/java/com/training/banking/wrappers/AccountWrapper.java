package com.training.banking.wrappers;

import com.training.banking.model.Account;

/**
 * @author Pratyush Gupta
 *
 */
public class AccountWrapper {

	private Integer bankId;
	private Integer customerId;
	private Account account;

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
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountWrapper() {
		super();
	}

	/**
	 * @param bankId
	 * @param customerId
	 * @param account
	 */
	public AccountWrapper(Integer bankId, Integer customerId, Account account) {
		super();
		this.bankId = bankId;
		this.customerId = customerId;
		this.account = account;
	}

	@Override
	public String toString() {
		return "AccountWrapper [bankId=" + bankId + ", customerId=" + customerId + ", account=" + account + "]";
	}

}
