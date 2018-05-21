package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	ITransactionService transactionService;
	
	@Autowired
	IAccountRepository accountRepo;

	@Autowired
	ICustomerRepository customerRepo;

	@Autowired
	IBankRepository bankRepo;

	@Override
	public Account createAccount(AccountWrapper accountWrapper) {
		
		Integer bankId = accountWrapper.getBankId();
		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();
		
		Integer customerId = accountWrapper.getCustomerId();
		Optional<Customer> customerPossible = customerRepo.findById(customerId);
		Customer customer = customerPossible.get();
		
		Account account = accountWrapper.getAccount();
		account.setBank(bank);
		account.setCustomer(customer);
		
		accountRepo.save(account);
		return account;
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
		return accountRepo.findById(accountId);
	}

}
