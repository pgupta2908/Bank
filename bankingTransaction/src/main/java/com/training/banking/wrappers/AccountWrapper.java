package com.training.banking.wrappers;

import com.training.banking.model.Account;

public class AccountWrapper {

	private Integer bankId;
	private Integer customerId;
	private Account account;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountWrapper() {
		super();
	}

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
