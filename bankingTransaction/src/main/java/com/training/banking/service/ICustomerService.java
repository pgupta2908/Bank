package com.training.banking.service;

import java.util.Optional;

import com.training.banking.model.Customer;
import com.training.banking.wrappers.CustomerWrapper;

public interface ICustomerService {

	public Customer createCustomer(CustomerWrapper customerWrapper);

	public Optional<Customer> getCustomerDetails(Integer customerId);

}
