package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.DepositMoneyException;
import com.training.banking.model.ATM;
import com.training.banking.model.Bank;
import com.training.banking.repository.IATMRepository;
import com.training.banking.repository.IBankRepository;
import com.training.banking.wrappers.ATMWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@Service
public class ATMServiceImpl implements IATMService {

	Logger log = Logger.getLogger(ATMServiceImpl.class.getName());

	@Autowired
	IATMRepository atmRepo;

	@Autowired
	IBankRepository bankRepo;

	@Autowired
	Environment env;

	/*
	 * method to create a new atm
	 */
	@Override
	public ATM createATM(ATMWrapper atmWrapper) {

		try {
			// check for null value of atm wrapper object
			if (atmWrapper.equals(null)) {
				log.error("atm wrapper object passed is null");
				throw new CreationException(env.getProperty("creation01"));
			}

			// check for null and negative value of bank id and null value of atm object
			else if (atmWrapper.getBankId() <= 0 || atmWrapper.getAtm().equals(null)) {
				log.error("BankId is null or negative or atm object is null");
				throw new CreationException("Please ensure positive values for bankId and null atm");
			}

			else {
				Integer bankId = atmWrapper.getBankId();
				Optional<Bank> bankPossible = bankRepo.findById(bankId);

				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error("bank object does not exist");
					throw new CreationException("bank does not exist with corresponding bank id");
				}

				// when everything is correct
				else {

					Bank bank = bankPossible.get();

					ATM atm = atmWrapper.getAtm();
					atm.setBank(bank);

					atmRepo.save(atm);
					return atm;
				}
			}
		} catch (CreationException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public ATM addMoneyFromBank(Integer atmId, BigDecimal amount) {

		try {
			// check for null and negative values of atmId and amount
			if (atmId <= 0 || amount.compareTo(new BigDecimal(0)) == -1) {
				log.error("some field value of bank is null or negative");
				throw new DepositMoneyException("Some field values are currently null or negative");
			}

			Optional<ATM> atmPossible = atmRepo.findById(atmId);

			boolean atmPresence = atmPossible.isPresent();

			// check for presence of atm
			if (atmPresence == false) {
				log.error("atm object does not exist");
				throw new DepositMoneyException("atm does not exist with atm id " + atmId);
			}

			ATM atm = atmPossible.get();
			BigDecimal atmInitialBalance = atm.getAmount();
			BigDecimal atmUpdatedBalance = atmInitialBalance.add(amount);
			atm.setAmount(atmUpdatedBalance);

			Integer bankId = atm.getBank().getBankId();
			Optional<Bank> bankPossible = bankRepo.findById(bankId);
			Bank bank = bankPossible.get();
			BigDecimal bankInitialBalance = bank.getAmount();
			BigDecimal bankUpdatedBalance = bankInitialBalance.subtract(amount);
			bank.setAmount(bankUpdatedBalance);

			atmRepo.save(atm);
			return atm;
		} catch (DepositMoneyException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public ATM withdrawMoney(Integer atmId, BigDecimal amount) {

		Optional<ATM> atmPossible = atmRepo.findById(atmId);
		ATM atm = atmPossible.get();
		BigDecimal atmInitialBalance = atm.getAmount();
		BigDecimal atmUpdatedBalance = atmInitialBalance.subtract(amount);
		atm.setAmount(atmUpdatedBalance);

		/*
		 * Integer bankId = atm.getBank().getBankId(); Optional<Bank> bankPossible =
		 * bankRepo.findById(bankId); Bank bank = bankPossible.get(); BigDecimal
		 * bankInitialBalance = bank.getAmount(); BigDecimal bankUpdatedBalance =
		 * bankInitialBalance.add(amount); bank.setAmount(bankUpdatedBalance);
		 */

		atmRepo.save(atm);
		return atm;
	}

}
