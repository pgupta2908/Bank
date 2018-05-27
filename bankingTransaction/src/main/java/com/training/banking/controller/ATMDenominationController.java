/*package com.training.banking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.banking.model.ATMDenomination;
import com.training.banking.service.IATMDenominationService;
import com.training.banking.wrappers.ATMDenominationWrapper;

@RestController
@RequestMapping(value = "/atmDenm")
public class ATMDenominationController {

	@Autowired
	IATMDenominationService atmDenominationService;

	*//**
	 * @param atmDenominationWrapper
	 * @return
	 *//*
	@PostMapping(value = "/create")
	public ResponseEntity<ATMDenomination> createATMDenomination(
			@RequestBody ATMDenominationWrapper atmDenominationWrapper) {
		ATMDenomination createdATMDenomination = atmDenominationService.createAtmDenomination(atmDenominationWrapper);

		if (createdATMDenomination != null)
			return new ResponseEntity<ATMDenomination>(createdATMDenomination, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	*//**
	 * @param atmDenominationId
	 * @return
	 *//*
	@GetMapping(value = "/getById")
	public ResponseEntity<Optional<ATMDenomination>> getATMDenominationDetails(Integer atmDenominationId) {
		Optional<ATMDenomination> atmDenomination = atmDenominationService.getAtmDenominationDetails(atmDenominationId);

		if (atmDenomination != null)
			return new ResponseEntity<Optional<ATMDenomination>>(atmDenomination, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	*//**
	 * @param atmDenominationId
	 * @param name
	 * @param pin
	 * @return
	 * @throws CloneNotSupportedException
	 *//*
	@PutMapping(value = "updateById/{id}/{count}")
	public ResponseEntity<ATMDenomination> updateATMDenominationDetail(@PathVariable(value = "id") Integer denomination,
			@PathVariable(value = "count") Integer noOfDenomination) throws CloneNotSupportedException {
		ATMDenomination updatedAtmDenomination = atmDenominationService.updateAtmDenomination(denomination,
				noOfDenomination);

		if (updatedAtmDenomination != null)
			return new ResponseEntity<ATMDenomination>(updatedAtmDenomination, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
*/