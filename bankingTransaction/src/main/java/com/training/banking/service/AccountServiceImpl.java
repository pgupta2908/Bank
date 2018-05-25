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
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for not null value of AccountWrapper");
			}

			// check for null or negative value of bank id, customer id and null value of
			// account object
			else if (accountWrapper.getBankId() <= 0 || accountWrapper.getCustomerId() <= 0
					|| accountWrapper.getAccount().equals(null)) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException(
						"Please check for positive values for bankId, customerId and not null customer");
			}

			// check for already existing account
			if (accountWrapper.getAccount().getAccountId() != null) {
				Optional<Account> accountPossible = accountRepo.findByAccountId(accountWrapper.getAccount().getAccountId());
				if (accountPossible.isPresent()) {
					log.error(env.getProperty("alreadyExists"));
					throw new CreationException("account object already exists");
				}
			}

			else {
				Integer bankId = accountWrapper.getBankId();
				Optional<Bank> bankPossible = bankRepo.findByBankId(bankId);

				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("bank does not exist with corresponding bank id");
				}

				Integer customerId = accountWrapper.getCustomerId();
				Optional<Customer> customerPossible = customerRepo.findByCustomerId(customerId);

				boolean customerPresence = customerPossible.isPresent();

				// check for presence of customer object
				if (customerPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("customer does not exist with corresponding customer id");
				}

				else {
					Bank bank = bankPossible.get();
					log.info("bank object is present");

					Customer customer = customerPossible.get();
					log.info("customer object is present");

					Account account = accountWrapper.getAccount();
					account.setBank(bank);
					account.setCustomer(customer);

					BigDecimal bankBalance = bank.getAmount().add(account.getAmount());
					bank.setAmount(bankBalance);

					log.info("Account created successfully");
					return accountRepo.save(account);
				}

			}
		} catch (CreationException e) {
			log.error("Account Creation Exception " + e.getMessage());
			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("Account Creation Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("Account Creation Exception " + e.getMessage());
			return null;
		}
		return null;
	}

	/*
	 * method to deposit money to an account
	 */
	@Override
	public Account depositMoney(Integer accountId, BigDecimal amount) {

		try {
			// check for null or negative values of accountId and amount
			if (accountId <= 0 || amount.compareTo(new BigDecimal(0)) == -1) {
				log.error(env.getProperty("NullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of account Id and amont");
			}

			Optional<Account> accountPossible = accountRepo.findByAccountId(accountId);
			boolean accountPresence = accountPossible.isPresent();

			// check for presence of account object
			if (accountPresence == false) {
				log.error(env.getProperty("notFound"));
				throw new NotFoundException("account does not exist with the corresponding account Id");
			}

			else {
				Account account = accountPossible.get();
				BigDecimal accountInitialBalance = account.getAmount();

				Integer bankId = account.getBank().getBankId();

				Optional<Bank> bankPossible = bankRepo.findByBankId(bankId);
				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("bank does not exist with corresponding id");
				}

				Bank bank = bankPossible.get();

				BigDecimal accountUpdatedBalance = accountInitialBalance.add(amount);
				account.setAmount(accountUpdatedBalance);

				BigDecimal bankInitialBalance = bank.getAmount();
				BigDecimal bankUpdatedBalance = bankInitialBalance.add(amount);
				bank.setAmount(bankUpdatedBalance);

				transactionService.createTransaction(account.getCustomer().getCustomerId(), accountId, amount,
						env.getProperty("cr"));
				log.info("amount depositted succussfully");
				accountRepo.save(account);

				return account;
			}
		} catch (NullOrNegativeValuesException e) {
			log.error("Account deposit Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("Account deposit Exception " + e.getMessage());
			return null;
		}
	}

	/*
	 * method to withdraw money from an account
	 */
	@Override
	public Account withdrawMoney(Integer accountId, BigDecimal amount) {
		try {
			// check for null or negative values of accountId and amount
			if (accountId <= 0 || amount.compareTo(new BigDecimal(0)) == -1) {
				log.error(env.getProperty("NullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive values of account Id and amont");
			}

			Optional<Account> accountPossible = accountRepo.findByAccountId(accountId);
			boolean accountPresence = accountPossible.isPresent();

			// check for presence of account object
			if (accountPresence == false) {
				log.error(env.getProperty("notFound"));
				throw new NotFoundException("account does not exist with corresponding accountId");
			}

			else {
				Account account = accountPossible.get();
				BigDecimal accountInitialBalance = account.getAmount();

				// check for presence of balance in account to be withdrawn
				if (accountInitialBalance.compareTo(amount) == -1) {
					log.error(env.getProperty("lowBalance"));
					throw new LowBalanceException("The account does not have enough funds");
				}

				Integer bankId = account.getBank().getBankId();

				Optional<Bank> bankPossible = bankRepo.findByBankId(bankId);
				boolean bankPresence = bankPossible.isPresent();

				// check for presence of bank object
				if (bankPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("bank does not exist with id " + bankId);
				}

				Bank bank = bankPossible.get();
				BigDecimal bankInitialBalance = bank.getAmount();

				// check for presence of balance in bank to be withdrawn
				if (bankInitialBalance.compareTo(amount) == -1) {
					log.error(env.getProperty("lowBalance"));
					throw new LowBalanceException("The bank does not have enough funds");
				}

				BigDecimal accountUpdatedBalance = accountInitialBalance.subtract(amount);
				account.setAmount(accountUpdatedBalance);

				BigDecimal bankUpdatedBalance = bankInitialBalance.subtract(amount);
				bank.setAmount(bankUpdatedBalance);

				transactionService.createTransaction(account.getCustomer().getCustomerId(), accountId, amount,
						env.getProperty("dr"));
				log.info("withdraw from account successful");
				accountRepo.save(account);
				return account;
			}
		} catch (NullOrNegativeValuesException e) {
			log.error("Account withdraw Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("Account withdraw Exception " + e.getMessage());
			return null;
		} catch (LowBalanceException e) {
			log.error("Account withdraw Exception " + e.getMessage());
			return null;
		}
	}

	/*
	 * method to get account details of an account
	 */
	@Override
	public Optional<Account> getAccountDetails(Integer accountId) {
		try {
			Optional<Account> accountPossible = accountRepo.findByAccountId(accountId);

			boolean accountPresence = accountPossible.isPresent();

			// check for null and negative value of accountId
			if (accountId <= 0) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for positive value of account Id");
			}

			// check if account exists or not
			if (accountPresence == false) {
				log.error(env.getProperty("notFound"));
				throw new NotFoundException("Account does not exist for the corresponding customer Id");
			}

			// when everything is correct
			else {
				log.info("account found with customerId " + accountId);
				return accountRepo.findByAccountId(accountId);
			}
		} catch (NotFoundException e) {
			log.error("Account get Details Exception " + e.getMessage());
			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("Customer get Details Exception " + e.getMessage());
			return null;
		}
	}

}
