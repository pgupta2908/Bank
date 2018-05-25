package com.training.banking.controller;

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

import com.training.banking.model.Customer;
import com.training.banking.service.ICustomerService;
import com.training.banking.wrappers.CustomerWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	/**
	 * @param customerWrapper
	 * @return
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody CustomerWrapper customerWrapper) {
		Customer createdCustomer = customerService.createCustomer(customerWrapper);

		if (createdCustomer != null)
			return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * @param customerId
	 * @return
	 */
	@GetMapping(value = "/getById")
	public ResponseEntity<Optional<Customer>> getCustomerDetails(Integer customerId) {
		Optional<Customer> customer = customerService.getCustomerDetails(customerId);

		if (customer != null)
			return new ResponseEntity<Optional<Customer>>(customer, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param customerId
	 * @param name
	 * @param pin
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	@PutMapping(value = "updateById/{id}/{name}/{pin}")
	public ResponseEntity<Customer> updateCustomerDetail(@PathVariable(value = "id") Integer customerId,
			@PathVariable(value = "name") String name, @PathVariable(value = "pin") Integer pin) throws CloneNotSupportedException {
		Customer updatedCustomer = customerService.updateCustomerDetail(customerId, name, pin);
		
		if(updatedCustomer != null)
			return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
