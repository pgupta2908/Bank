package com.training.banking.service;

import java.util.Optional;

import com.training.banking.model.Customer;
import com.training.banking.wrappers.CustomerWrapper;

/**
 * @author Pratyush Gupta
 *
 */
public interface ICustomerService {

	/**
	 * @param customerWrapper
	 * @return
	 */
	public Customer createCustomer(CustomerWrapper customerWrapper);

	/**
	 * @param customerId
	 * @return
	 */
	public Optional<Customer> getCustomerDetails(Integer customerId);

	/**
	 * @param customerId
	 * @param name
	 * @param pin
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public Customer updateCustomerDetail(Integer customerId, String name, Integer pin) throws CloneNotSupportedException;

}
