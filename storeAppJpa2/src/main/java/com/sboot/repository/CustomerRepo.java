package com.sboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.sboot.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	//public void addCustomer(int customerId, String customerName, String customerAddress, String paymentMode);
}
