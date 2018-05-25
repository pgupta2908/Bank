package com.training.banking.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.NotFoundException;
import com.training.banking.exception.NullOrNegativeValuesException;
import com.training.banking.model.Account;
import com.training.banking.model.Transaction;
import com.training.banking.repository.IAccountRepository;
import com.training.banking.repository.ICustomerRepository;
import com.training.banking.repository.ITransactionRepository;

/**
 * @author Pratyush Gupta
 *
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

	Logger log = Logger.getLogger(TransactionServiceImpl.class.getName());

	@Autowired
	ITransactionRepository transactionRepo;

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IAccountRepository accountRepo;

	@Autowired
	Environment env;

	/*
	 * @Override public Transaction createTransaction(TransactionWrapper
	 * transactionWrapper) { Integer customerId =
	 * transactionWrapper.getCustomerId(); Optional<Customer> customerPossible =
	 * customerRepo.findById(customerId); Customer customer =
	 * customerPossible.get();
	 * 
	 * Integer accountId = transactionWrapper.getAccountId(); Optional<Account>
	 * accountPossible = accountRepo.findById(accountId); Account account =
	 * accountPossible.get(); System.out.println(
	 * "-------------------------------------------------------------"); Transaction
	 * transaction = transactionWrapper.getTransaction();
	 * transaction.setCustomer(customer); transaction.setAccount(account);
	 * System.out.println(transaction); transactionRepo.save(transaction); return
	 * transaction; }
	 */

	/*
	 * method to create transaction
	 */
	@Override
	public Transaction createTransaction(Integer customerId, Integer accountId, BigDecimal amount,
			String transactionType) {

		Transaction transaction = new Transaction();
		transaction.setCustomerId(customerId);
		transaction.setAccountId(accountId);
		transaction.setAmount(amount);
		transaction.setTransactionType(transactionType);

		transactionRepo.save(transaction);
		return transaction;
	}

	/*
	 * method to generate transaction report for an account
	 */
	@Override
	public List<Transaction> generateTransactionReport(Integer customerId, Integer accountId) {

		try {
			// check for null or negative values of customerId and accountId
			if (accountId <= 0 || customerId <= 0) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please enter positive values only");
			}

			Optional<Account> accountPossible = accountRepo.findByAccountId(accountId);
			boolean accountPresence = accountPossible.isPresent();
			// check for presence of account
			if (accountPresence == false) {
				log.error(env.getProperty("notFound"));
				throw new NotFoundException("The account corresponding to account id does not exist");
			}

			Optional<Transaction> transactionList = transactionRepo.findByTransactionId(customerId);
//			findAllById(customerId);
			Transaction transaction = transactionList.get();
			System.out.println(transaction);
			List<Transaction> list = new ArrayList<>();
			list.add(transaction);
			return list;
		} catch (NullOrNegativeValuesException e) {
			log.error(e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error(e.getMessage());
			return null;
		}

		// return transactionList;
	}

}
