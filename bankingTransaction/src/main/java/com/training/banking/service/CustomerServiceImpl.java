package com.training.banking.service;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.NotFoundException;
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

	Logger log = Logger.getLogger(CustomerServiceImpl.class.getName());

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IBankRepository bankRepo;

	@Autowired
	Environment env;

	/*
	 * method to create a new customer
	 */
	@Override
	public Customer createCustomer(CustomerWrapper customerWrapper) {

		try {

			// check for null value of customer wrapper object
			if (customerWrapper.equals(null)) {
				log.error("customer wrapper object passed is null");
				throw new CreationException(env.getProperty("creation01"));
			}

			// check for null and negative value of bank id and null value of customer object
			else if (customerWrapper.getBankId() <= 0 || customerWrapper.getCustomer().equals(null)) {
				log.error("BankId is null or negative or customer object is null");
				throw new CreationException("Please ensure positive values for bankId and null customer");
			}

			else {
				Integer bankId = customerWrapper.getBankId();
				Optional<Bank> bankPossible = bankRepo.findById(bankId);

				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error("bank object does not exist");
					throw new CreationException("bank does not exist with corresponding bank id");
				}

				// when everything is correct
				else {
					log.info("bank object is present");
					Bank bank = bankPossible.get();
					Customer customer = customerWrapper.getCustomer();
					customer.setBank(bank);

					customerRepo.save(customer);

					return customer;
				}
			}
		} catch (CreationException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Optional<Customer> getCustomerDetails(Integer customerId) {

		try {
			Optional<Customer> customerPossible = customerRepo.findById(customerId);

			boolean customerPresence = customerPossible.isPresent();

			// check for null and negative value of customerId
			if (customerId <= 0) {
				log.error("negative or null values for customerId is entered");
				throw new NotFoundException("Please enter positive value only");
			}

			// check if the customer exists or not
			if (customerPresence == false) {
				log.error("No customer exists with customerId " + customerId);
				throw new NotFoundException("Please enter an existing customerId");
			}

			// when everything is correct
			else {
				log.info("customer found with customerId " + customerId);
				return customerRepo.findById(customerId);
			}
		} catch (NotFoundException e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
