package com.training.banking.service;

import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.NotFoundException;
import com.training.banking.exception.NullOrNegativeValuesException;
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
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for customerWrapper for not null");
			}

			// check for null and negative value of bank id and null value of customer
			// object
			else if (customerWrapper.getBankId() <= 0 || customerWrapper.getCustomer().equals(null)) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException(
						"Please check for positive values for bankId and null customer");
			}

			// check for already existing customer
			if (customerWrapper.getCustomer().getCustomerId() != null) {
				Optional<Customer> customerPossible = customerRepo
						.findById(customerWrapper.getCustomer().getCustomerId());
				if (customerPossible.isPresent()) {
					log.error(env.getProperty("alreadyExists"));
					throw new CreationException("customer object already exists");
				}
			}

			else {
				Integer bankId = customerWrapper.getBankId();
				Optional<Bank> bankPossible = bankRepo.findById(bankId);

				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("bank does not exist with corresponding bank id");
				}

				// when everything is correct
				else {
					Bank bank = bankPossible.get();
					log.info("bank object is present");
					Customer customer = customerWrapper.getCustomer();
					bank.setUserId(customer.getUserId());
					customer.setBank(bank);

					log.info("Customer created successfully");
					return customerRepo.save(customer);
				}
			}

		} catch (NullOrNegativeValuesException e) {
			log.error("Customer Creation Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("Customer Creation Exception " + e.getMessage());
			return null;
		} catch (CreationException e) {
			log.error("Customer Creation Exception " + e.getMessage());
			return null;
		}
		return null;
	}

	/*
	 * method to get details of a customer
	 */
	@Override
	public Optional<Customer> getCustomerDetails(Integer customerId) {

		try {
			Optional<Customer> customerPossible = customerRepo.findById(customerId);

			boolean customerPresence = customerPossible.isPresent();

			// check for null and negative value of customerId
			if (customerId <= 0) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive value of customer Id");
			}

			// check if the customer exists or not
			if (customerPresence == false) {
				log.error(env.getProperty("notFound"));
				throw new NotFoundException("Customer does not exist for the corresponding customer Id");
			}

			// when everything is correct
			else {
				log.info("customer found with customerId " + customerId);
				return customerRepo.findById(customerId);
			}
		} catch (NotFoundException e) {
			log.error("Customer get Details Exception " + e.getMessage());
			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("Customer get Details Exception " + e.getMessage());
			return null;
		}
	}

	@Override
	public Customer updateCustomerDetail(Integer customerId, String name, Integer pin) {
		try {
			// check for null or negative value of customerId
			if (customerId <= 0) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of customer id");
			}

			// check for null values of name and pin
			if (name == null || pin <= 0) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of customer id");
			}

			Optional<Customer> customerPossible = customerRepo.findById(customerId);
			boolean customerPresence = customerPossible.isPresent();

			// check for existence of customer object
			if (customerPresence == false) {
				log.error(env.getProperty("nullObject"));
				throw new NotFoundException("Please check fot not null values of customer");
			}

			// when everything is correct
			else {
				Customer updatedCustomer = customerPossible.get();
				updatedCustomer.setName(name);
				updatedCustomer.setPin(pin);
				return customerRepo.save(updatedCustomer);
			}

		} catch (NotFoundException e) {
			log.error("Customer get Details Exception " + e.getMessage());
//			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("Customer get Details Exception " + e.getMessage());
//			return null;
		}

		return null;
	}

}
