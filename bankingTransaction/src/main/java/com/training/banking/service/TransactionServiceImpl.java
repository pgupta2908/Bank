package com.training.banking.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.banking.model.Transaction;
import com.training.banking.repository.IAccountRepository;
import com.training.banking.repository.ICustomerRepository;
import com.training.banking.repository.ITransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	ITransactionRepository transactionRepo;

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IAccountRepository accountRepo;

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

	@Override
	public List<Transaction> generateTransactionReport(Integer customerId, Integer accountId) {
		System.out.println("---------------------------------------");
		Optional<Transaction> transactionList = transactionRepo.findById(customerId);/*findAllById(customerId);*/
		Transaction transaction = transactionList.get();
		System.out.println(transaction);
		List<Transaction> list = new ArrayList<>();
		list.add(transaction);
		return list;

		// return transactionList;
	}

}
