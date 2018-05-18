package com.training.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.banking.model.Transaction;
import com.training.banking.service.ITransactionService;

@RestController(value = "/transaction")
public class TransactionController {

	@Autowired
	ITransactionService transactionService;

	/*@PostMapping(value = "/create")
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionWrapper transactionWrapper) {
		System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
		Transaction transaction = transactionService.createTransaction(transactionWrapper);
		System.out.println("lllllllllllllllllllllllllllllllllll");
		return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
	}*/

	@GetMapping(value = "/getReport")
	public ResponseEntity<List<Transaction>> generateTransactionReport(@RequestParam Integer customerId,
			@RequestParam Integer accountId) {
		List<Transaction> transactionList = transactionService.generateTransactionReport(customerId, accountId);
		return new ResponseEntity<List<Transaction>>(transactionList, HttpStatus.FOUND);
	}
}
