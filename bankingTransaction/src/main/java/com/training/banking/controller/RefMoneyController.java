/*package com.training.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.banking.model.RefMoney;
import com.training.banking.service.IRefMoneyService;

@RestController
@RequestMapping(value = "/refMoney")
public class RefMoneyController {

	@Autowired
	IRefMoneyService refMoneyService;

	@PostMapping(value = "/create")
	public ResponseEntity<RefMoney> createRefMoney(RefMoney refMoney) {
		RefMoney createdRefMoney = refMoneyService.createRefMoney(refMoney);
		
		if (createdRefMoney != null)
			return new ResponseEntity<RefMoney>(refMoney, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<RefMoney>> listAllRefMoney() {
		List<RefMoney> refMoneyList = refMoneyService.listAllRefMoney();
		
		if(refMoneyList!=null)
			return new ResponseEntity<List<RefMoney>>(refMoneyList, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
*/