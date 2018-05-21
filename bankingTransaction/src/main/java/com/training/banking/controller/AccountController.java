package com.training.banking.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.banking.model.Account;
import com.training.banking.service.IAccountService;
import com.training.banking.wrappers.AccountWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	IAccountService accountService;

	/**
	 * @param accountWrapper
	 * @return
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<Account> createAccount(@RequestBody AccountWrapper accountWrapper) {
		Account createdAccount = accountService.createAccount(accountWrapper);
		
		if(createdAccount != null)
			return new ResponseEntity<Account>(createdAccount, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * @param accountId
	 * @param amount
	 * @return
	 */
	@GetMapping(value = "/deposit")
	public ResponseEntity<Account> depositMoney(@RequestParam Integer accountId, @RequestParam BigDecimal amount) {
		Account updatedAccount = accountService.depositMoney(accountId, amount);
		if (updatedAccount != null)
			return new ResponseEntity<Account>(updatedAccount, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * @param accountId
	 * @param amount
	 * @return
	 */
	@GetMapping(value = "/withdraw")
	public ResponseEntity<Account> withdrawMoney(@RequestParam Integer accountId, @RequestParam BigDecimal amount) {
		Account updatedAccount = accountService.withdrawMoney(accountId, amount);
		if (updatedAccount != null)
			return new ResponseEntity<Account>(updatedAccount, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * @param accountId
	 * @return
	 */
	@GetMapping(value = "/getById")
	public ResponseEntity<Optional<Account>> getAccountDetails(@RequestParam Integer accountId) {
		Optional<Account> account = accountService.getAccountDetails(accountId);
		if (account != null)
			return new ResponseEntity<Optional<Account>>(account, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
