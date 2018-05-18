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

@RestController
@RequestMapping(value = "/bank")
public class BankController {

	@Autowired
	IBankService bankService;

	@PostMapping(value = "/create")
	public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
		Bank createdBank = bankService.createBank(bank);
		return new ResponseEntity<Bank>(createdBank, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getById")
	public ResponseEntity<Optional<Bank>> getBankDetails(@RequestParam Integer bankId) {
		Optional<Bank> bank = bankService.getBankDetails(bankId);
		return new ResponseEntity<Optional<Bank>>(bank, HttpStatus.FOUND);
	}
}
