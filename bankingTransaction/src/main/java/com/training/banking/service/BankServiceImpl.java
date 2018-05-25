package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.NotFoundException;
import com.training.banking.exception.NullOrNegativeValuesException;
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
			if (bank.equals(null)) {
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for bank to be not null");
			}
			// check for null and negative field values of bank object
			else if (/* bank.getBankId() <= 0 || */bank.getAmount().compareTo(new BigDecimal(0)) == -1) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of bank id and amount");
			}

			// check for already existing bank
			if (bank.getBankId() != null) {
				Optional<Bank> bankPossible = bankRepo.findByBankId(bank.getBankId());
				if (bankPossible.isPresent()) {
					log.error(env.getProperty("alreadyExists"));
					throw new CreationException("bank object already exists");
				}
			}

			// when everything is correct
			log.info("Bank created successfully");
			return bankRepo.save(bank);
		} catch (NullOrNegativeValuesException e) {
			log.error("Bank Creation Exception " + e.getMessage());
			return null;
		} catch (CreationException e) {
			log.error("Bank Creation Exception " + e.getMessage());
			return null;
		}
	}

	/*
	 * method to get the details of a bank
	 */
	@Override
	public Optional<Bank> getBankDetails(Integer bankId) {
		try {
			// check for null and negative value of bankId
			if (bankId <= 0) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of bankId");
			}

			Optional<Bank> bankPossible = bankRepo.findByBankId(bankId);

			boolean bankPresence = bankPossible.isPresent();

			// check if the bank exists or not
			if (bankPresence == false) {
				log.error(env.getProperty("nullObject"));
				throw new NotFoundException("bank object does not exist for the corresponding bank id");
			}

			// when everything is correct
			else {
				log.info("bank found with bankId" + bankId);
				return bankRepo.findByBankId(bankId);

			}
		} catch (NotFoundException e) {
			log.error("Bank get Details Exception " + e.getMessage());
			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("Bank get Details Exception " + e.getMessage());
			return null;
		}
	}

}
