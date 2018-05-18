package com.training.banking.wrappers;

import com.training.banking.model.Customer;

public class CustomerWrapper {

	Integer bankId;
	private Customer customer;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerWrapper() {
		super();
	}

	public CustomerWrapper(Integer bankId, Customer customer) {
		super();
		this.bankId = bankId;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerWrapper [bankId=" + bankId + ", customer=" + customer + "]";
	}

}
