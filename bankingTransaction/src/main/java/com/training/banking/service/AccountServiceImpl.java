package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.NotFoundException;
import com.training.banking.model.Account;
import com.training.banking.model.Bank;
import com.training.banking.model.Customer;
import com.training.banking.repository.IAccountRepository;
import com.training.banking.repository.IBankRepository;
import com.training.banking.repository.ICustomerRepository;
import com.training.banking.wrappers.AccountWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@Service
public class AccountServiceImpl implements IAccountService {

	Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

	@Autowired
	ITransactionService transactionService;

	@Autowired
	IAccountRepository accountRepo;

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IBankRepository bankRepo;

	@Autowired
	Environment env;

	/*
	 * method to add a new account
	 */
	@Override
	public Account createAccount(AccountWrapper accountWrapper) {

		try {

			// check for null value of accountWrapper object
			if (accountWrapper.equals(null)) {
				log.error("account wrapper object passed is null");
				throw new CreationException(env.getProperty("creation01"));
			}

			// check for null or negative value of bank id, customer id and null value of
			// account object
			else if (accountWrapper.getBankId() <= 0 || accountWrapper.getCustomerId() <= 0
					|| accountWrapper.getAccount().equals(null)) {
				log.error("bankId and customerId is null or negative and account object is n null");
				throw new CreationException("Please ensure positive values for bankId, customerId and null customer");
			}

			else {
				Integer bankId = accountWrapper.getBankId();
				Optional<Bank> bankPossible = bankRepo.findById(bankId);

				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error("bank object does not exist");
					throw new CreationException("bank does not exist with corresponding bank id");
				}

				Integer customerId = accountWrapper.getCustomerId();
				Optional<Customer> customerPossible = customerRepo.findById(customerId);

				boolean customerPresence = customerPossible.isPresent();

				// check for presence of customer object
				if (customerPresence == false) {
					log.error("customer object does not exist");
					throw new CreationException("customer does not exist with corresponding customer id");
				}

				else {
					log.info("bank object is present");
					Bank bank = bankPossible.get();
					Customer customer = customerPossible.get();

					log.info("customer object is present");
					Account account = accountWrapper.getAccount();
					account.setBank(bank);
					account.setCustomer(customer);

					accountRepo.save(account);
					return account;
				}

			}
		} catch (CreationException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Account depositMoney(Integer accountId, BigDecimal amount) {

		Optional<Account> accountPossible = accountRepo.findById(accountId);

		Account account = accountPossible.get();
		BigDecimal accountInitialBalance = account.getAmount();
		BigDecimal accountUpdatedBalance = accountInitialBalance.add(amount);
		account.setAmount(accountUpdatedBalance);

		Integer bankId = account.getBank().getBankId();

		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();
		BigDecimal bankInitialBalance = bank.getAmount();
		BigDecimal bankUpdatedBalance = bankInitialBalance.add(amount);
		bank.setAmount(bankUpdatedBalance);

		transactionService.createTransaction(account.getCustomer().getCustomerId(), accountId, amount, "credit");
		accountRepo.save(account);

		return account;
	}

	@Override
	public Account withdrawMoney(Integer accountId, BigDecimal amount) {

		Optional<Account> accountPossible = accountRepo.findById(accountId);

		Account account = accountPossible.get();
		BigDecimal accountInitialBalance = account.getAmount();
		BigDecimal accountUpdatedBalance = accountInitialBalance.subtract(amount);
		account.setAmount(accountUpdatedBalance);

		Integer bankId = account.getBank().getBankId();

		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();
		BigDecimal bankInitialBalance = bank.getAmount();
		BigDecimal bankUpdatedBalance = bankInitialBalance.subtract(amount);
		bank.setAmount(bankUpdatedBalance);

		transactionService.createTransaction(account.getCustomer().getCustomerId(), accountId, amount, "debit");
		accountRepo.save(account);
		return account;
	}

	@Override
	public Optional<Account> getAccountDetails(Integer accountId) {
		try {
			Optional<Account> accountPossible = accountRepo.findById(accountId);

			boolean accountPresence = accountPossible.isPresent();

			// check for null and negative value of accountId
			if (accountId <= 0) {
				log.error("negative or null values for accountId is entered");
				throw new NotFoundException("Please enter positive value only");
			}

			// check if account exists or not
			if (accountPresence == false) {
				log.error("No account exists with accountId " + accountId);
				throw new NotFoundException("Please enter an existing account id");
			}

			// when everything is correct
			else {
				return accountRepo.findById(accountId);
			}
		} catch (NotFoundException e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
