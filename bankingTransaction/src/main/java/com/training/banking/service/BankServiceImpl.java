package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.NotFoundException;
import com.training.banking.exception.CreationException;
import com.training.banking.model.Bank;
import com.training.banking.repository.IBankRepository;

/**
 * @author Pratyush Gupta
 *
 */
@Service
public class BankServiceImpl implements IBankService {

	Logger log = Logger.getLogger(BankServiceImpl.class.getName());

	@Autowired
	IBankRepository bankRepo;
	
	@Autowired
	Environment env;

	/*
	 * method to create a new bank
	 */
	@Override
	public Bank createBank(Bank bank) {

		try {
			// check for null value of bank object
			if (bank == null) {
				log.error("null object of bank");
				throw new CreationException(env.getProperty("creation01"));
			}

			// check for null and negative field values of bank object
			else if (bank.getBankId() <= 0 || bank.getAmount().compareTo(new BigDecimal(0)) == -1) {
				log.error("some field value of bank is null or negative");
				throw new CreationException("Some field values are currently null or negative");
			}

			Optional<Bank> bankPossible = bankRepo.findById(bank.getBankId());

			// check for already existing bank
			if (bankPossible.isPresent()) {
				log.error("bank object already exists");
				throw new CreationException(env.getProperty("exists01"));
			}

			// when everything is correct
			else
				log.info("bank object created");
			return bankRepo.save(bank);
		} catch (CreationException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/* 
	 * method to get the details of a bank
	 */
	@Override
	public Optional<Bank> getBankDetails(Integer bankId) {
		try {
			Optional<Bank> bankPossible = bankRepo.findById(bankId);

			boolean bankPresence = bankPossible.isPresent();

			//	check for null and negative value of bankId
			if (bankId <= 0)
			{
				log.error("negative or null values for bankId is entered");
				throw new NotFoundException("Please enter positive value only");
				/*env.getProperty("99")*/
			}
			
			//	check if the bank exists or not
			if (bankPresence == false) {
				log.error("no bank exists with id "+bankId);
				throw new NotFoundException("Please enter an existing bankId");
			}

			//	when everything is correct
			else {
				log.info("bank found with bankId" + bankId);
				return bankRepo.findById(bankId);

			}
		} catch (NotFoundException e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
