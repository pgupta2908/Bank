package com.sboot.service;

import java.util.List;

import com.sboot.model.Customer;

public interface CustomerService {

	public List<Customer> viewAllCustomer();
	
	public List<Customer> viewcustomerbyid(List<Integer> viewlist);
	
	public String addCustomers(List<Customer> cust);
	
	public Customer addaCustomers(Customer cust);
	
	public String removeCustomers(List<Integer> removeid);
	
	public String updateCustomers(List<Customer> cust);
	
}
