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

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	IAccountService accountService;

	@PostMapping(value = "/create")
	public ResponseEntity<Account> createAccount(@RequestBody AccountWrapper accountWrapper) {
		Account createdAccount = accountService.createAccount(accountWrapper);
		return new ResponseEntity<Account>(createdAccount, HttpStatus.CREATED);
	}

	@GetMapping(value = "/deposit")
	public ResponseEntity<Account> depositMoney(@RequestParam Integer accountId, @RequestParam BigDecimal amount) {
		Account updatedAccount = accountService.depositMoney(accountId, amount);
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/withdraw")
	public ResponseEntity<Account> withdrawMoney(@RequestParam Integer accountId, @RequestParam BigDecimal amount) {
		Account updatedAccount = accountService.withdrawMoney(accountId, amount);
		return new ResponseEntity<Account>(updatedAccount, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/getById")
	public ResponseEntity<Optional<Account>> getAccountDetails(@RequestParam Integer accountId) {
		Optional<Account> account = accountService.getAccountDetails(accountId);
		return new ResponseEntity<Optional<Account>>(account, HttpStatus.FOUND);
	}

}
