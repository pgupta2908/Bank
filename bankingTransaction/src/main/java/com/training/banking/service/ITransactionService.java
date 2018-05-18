package com.training.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.training.banking.model.Transaction;

public interface ITransactionService {

	/*
	 * public Transaction createTransaction(TransactionWrapper transactionWrapper);
	 */

	public Transaction createTransaction(Integer customerId, Integer accountId, BigDecimal amount, String transactionType);
	
	public List<Transaction> generateTransactionReport(Integer customerId, Integer accountId);

}
