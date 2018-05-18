package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.training.banking.model.Account;
import com.training.banking.wrappers.AccountWrapper;

public interface IAccountService {

	public Account createAccount(AccountWrapper accountWrapper);

	public Account depositMoney(Integer accountId, BigDecimal amount);

	public Account withdrawMoney(Integer accountId, BigDecimal amount);

	public Optional<Account> getAccountDetails(Integer accountId);

}
