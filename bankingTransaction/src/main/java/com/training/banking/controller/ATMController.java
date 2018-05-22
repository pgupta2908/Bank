package com.training.banking.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.banking.model.ATM;
import com.training.banking.service.IATMService;
import com.training.banking.wrappers.ATMWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@RestController
@RequestMapping(value = "/atm")
public class ATMController {

	@Autowired
	IATMService atmService;

	/**
	 * @param atmWrapper
	 * @return
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<ATM> createATM(@RequestBody ATMWrapper atmWrapper) {
		ATM createdAtm = atmService.createATM(atmWrapper);

		if (createdAtm != null)
			return new ResponseEntity<ATM>(createdAtm, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * @param atmId
	 * @param amount
	 * @return
	 */
	@GetMapping(value = "/addMoney")
	public ResponseEntity<ATM> addMoneyFromBank(@RequestParam Integer atmId, @RequestParam BigDecimal amount) {
		ATM updatedAtm = atmService.addMoneyFromBank(atmId, amount);

		if (updatedAtm != null)
			return new ResponseEntity<ATM>(updatedAtm, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/**
	 * @param atmId
	 * @param amount
	 * @return
	 */
	@GetMapping(value = "/withdraw")
	public ResponseEntity<ATM> withdrawMoney(@RequestParam Integer atmId, @RequestParam BigDecimal amount) {
		ATM updatedAtm = atmService.withdrawMoney(atmId, amount);
		if (updatedAtm != null)
			return new ResponseEntity<ATM>(updatedAtm, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
