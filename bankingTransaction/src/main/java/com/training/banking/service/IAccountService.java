package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.training.banking.model.Account;
import com.training.banking.wrappers.AccountWrapper;

/**
 * @author Pratyush Gupta
 *
 */
public interface IAccountService {

	/**
	 * @param accountWrapper
	 * @return
	 */
	public Account createAccount(AccountWrapper accountWrapper);

	/**
	 * @param accountId
	 * @param amount
	 * @return
	 */
	public Account depositMoney(Integer accountId, BigDecimal amount);

	/**
	 * @param accountId
	 * @param amount
	 * @return
	 */
	public Account withdrawMoney(Integer accountId, BigDecimal amount);

	/**
	 * @param accountId
	 * @return
	 */
	public Optional<Account> getAccountDetails(Integer accountId);

}
