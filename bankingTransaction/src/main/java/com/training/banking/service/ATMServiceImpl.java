package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.LowBalanceException;
import com.training.banking.exception.NotFoundException;
import com.training.banking.exception.NullOrNegativeValuesException;
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
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for atmWrapper for not null");
			}

			// check for null and negative value of bank id and null value of atm object
			else if (atmWrapper.getBankId() <= 0 || atmWrapper.getAtm().equals(null)) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values for bankId and null atm");
			}

			// check for already existing atm
			if (atmWrapper.getAtm().getAtmId() != null) {
				Optional<ATM> atmPossible = atmRepo.findById(atmWrapper.getAtm().getAtmId());
				if (atmPossible.isPresent()) {
					log.error(env.getProperty("alreadyExists"));
					throw new CreationException("atm object already exists");
				}
			}

			else {
				Integer bankId = atmWrapper.getBankId();
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

					ATM atm = atmWrapper.getAtm();

					/*
					 * // Updating the bank balance by adding amount of the new atm created
					 * BigDecimal bankBalace =bank.getAmount().add(atm.getAmount());
					 * bank.setAmount(bankBalace);
					 */
					atm.setBank(bank);

					log.info("ATM added successfully");
					return atmRepo.save(atm);
				}
			}
		} catch (CreationException e) {
			log.error("ATM Creation Exception " + e.getMessage());
			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("ATM Creation Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("ATM Creation Exception " + e.getMessage());
			return null;
		}
		return null;
	}

	/*
	 * method to add money from bank to atm
	 */
	@Override
	public ATM addMoneyFromBank(Integer atmId, BigDecimal amount) {

		try {
			// check for null and negative values of atmId and amount
			if (atmId <= 0 || amount.compareTo(new BigDecimal(0)) == -1) {
				log.error(env.getProperty("NullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of atm Id and amont");
			}

			Optional<ATM> atmPossible = atmRepo.findById(atmId);

			boolean atmPresence = atmPossible.isPresent();

			// check for presence of atm object
			if (atmPresence == false) {
				log.error(env.getProperty("notFound"));
				throw new NotFoundException("atm does not exist with corresponding amt Id");
			}

			else {
				ATM atm = atmPossible.get();

				Integer bankId = atm.getBank().getBankId();
				Optional<Bank> bankPossible = bankRepo.findById(bankId);

				boolean bankPresesnce = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresesnce == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("bank does not exist with corresponding id");
				}

				else {

					Bank bank = bankPossible.get();
					BigDecimal bankInitialBalance = bank.getAmount();

					// check for presence of amount in bank to be added in atm
					if (bankInitialBalance.compareTo(amount) == -1) {
						log.error("lowBalance");
						throw new LowBalanceException("Please try again with lower amount, bank is low on funds");
					}

					BigDecimal atmInitialBalance = atm.getAmount();
					BigDecimal atmUpdatedBalance = atmInitialBalance.add(amount);
					atm.setAmount(atmUpdatedBalance);

					BigDecimal bankUpdatedBalance = bankInitialBalance.subtract(amount);
					bank.setAmount(bankUpdatedBalance);

					log.info("ATM deposit from bank successful");
					atmRepo.save(atm);
					return atm;
				}
			}
		} catch (NullOrNegativeValuesException e) {
			log.error("ATM add Money Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("ATM add Money Exception " + e.getMessage());
			return null;
		} catch (LowBalanceException e) {
			log.error("ATM add Money Exception " + e.getMessage());
			return null;
		}
	}

	/*
	 * method to withdraw money from atm
	 */
	@Override
	public ATM withdrawMoney(Integer atmId, BigDecimal amount) {

		try {
			// check for null and negative values of atmId and amount
			if (atmId <= 0 || amount.compareTo(new BigDecimal(0)) == -1) {
				log.error(env.getProperty("NullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of atm Id and amont");
			}

			Optional<ATM> atmPossible = atmRepo.findById(atmId);

			boolean atmPresesnce = atmPossible.isPresent();

			// check for presence of atm object
			if (atmPresesnce == false) {
				log.error(env.getProperty("nullObject"));
				throw new NotFoundException("atm does not exist with corresponding id");
			}

			else {
				ATM atm = atmPossible.get();
				BigDecimal atmInitialBalance = atm.getAmount();

				// check for presence of amount in atm to be withdrawn
				if (atmInitialBalance.compareTo(amount) == -1) {
					log.error("lowBalance");
					throw new LowBalanceException("Please try again with lower amount, bank is low on funds");
				}

				BigDecimal atmUpdatedBalance = atmInitialBalance.subtract(amount);
				atm.setAmount(atmUpdatedBalance);

				/*
				 * Integer bankId = atm.getBank().getBankId(); Optional<Bank> bankPossible =
				 * bankRepo.findById(bankId); Bank bank = bankPossible.get(); BigDecimal
				 * bankInitialBalance = bank.getAmount(); BigDecimal bankUpdatedBalance =
				 * bankInitialBalance.add(amount); bank.setAmount(bankUpdatedBalance);
				 */

				log.info("ATM withdrawl successful");
				atmRepo.save(atm);
				return atm;
			}
		} catch (NullOrNegativeValuesException e) {
			log.error("ATM withdraw Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("ATM withdraw Exception " + e.getMessage());
			return null;
		} catch (LowBalanceException e) {
			log.error("ATM withdraw Exception " + e.getMessage());
			return null;
		}
	}

}
