package com.training.banking.service;

import java.math.BigDecimal;
import java.util.List;

import com.training.banking.model.Transaction;

/**
 * @author Pratyush Gupta
 *
 */
public interface ITransactionService {

	/**
	 * @param customerId
	 * @param accountId
	 * @param amount
	 * @param transactionType
	 * @return
	 */
	public Transaction createTransaction(Integer customerId, Integer accountId, BigDecimal amount,
			String transactionType);

	/**
	 * @param customerId
	 * @param accountId
	 * @return
	 */
	public List<Transaction> generateTransactionReport(Integer customerId, Integer accountId);

}
