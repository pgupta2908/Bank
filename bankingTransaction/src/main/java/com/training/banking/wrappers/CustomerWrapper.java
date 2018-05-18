package com.training.banking.wrappers;

import com.training.banking.model.Customer;

/**
 * @author Pratyush Gupta
 *
 */
public class CustomerWrapper {

	private Integer bankId;
	private Customer customer;

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
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerWrapper() {
		super();
	}

	/**
	 * @param bankId
	 * @param customer
	 */
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
