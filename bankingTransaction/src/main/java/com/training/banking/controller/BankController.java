package com.training.banking.controller;

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

import com.training.banking.model.Bank;
import com.training.banking.service.IBankService;

/**
 * @author Pratyush Gupta
 *
 */
@RestController
@RequestMapping(value = "/bank")
public class BankController {

	@Autowired
	IBankService bankService;

	/**
	 * @param bank
	 * @return
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
		Bank createdBank = bankService.createBank(bank);

		if (createdBank != null)
			return new ResponseEntity<Bank>(createdBank, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * @param bankId
	 * @return
	 */
	@GetMapping(value = "/getById")
	public ResponseEntity<Optional<Bank>> getBankDetails(@RequestParam Integer bankId) {
		Optional<Bank> bank = bankService.getBankDetails(bankId);

		if (bank != null)
			return new ResponseEntity<Optional<Bank>>(bank, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
