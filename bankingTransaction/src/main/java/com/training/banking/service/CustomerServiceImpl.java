package com.training.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.banking.model.Bank;
import com.training.banking.model.Customer;
import com.training.banking.repository.IBankRepository;
import com.training.banking.repository.ICustomerRepository;
import com.training.banking.wrappers.CustomerWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IBankRepository bankRepo;

	@Override
	public Customer createCustomer(CustomerWrapper customerWrapper) {
		Integer bankId = customerWrapper.getBankId();
		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();

		Customer customer = customerWrapper.getCustomer();
		customer.setBank(bank);

		customerRepo.save(customer);

		return customer;
	}

	@Override
	public Optional<Customer> getCustomerDetails(Integer customerId) {
		return customerRepo.findById(customerId);
	}

}
