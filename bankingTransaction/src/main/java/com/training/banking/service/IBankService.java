package com.training.banking.service;

import java.util.Optional;

import com.training.banking.model.Bank;

/**
 * @author Pratyush Gupta
 *
 */
public interface IBankService {

	/**
	 * @param bank
	 * @return
	 */
	public Bank createBank(Bank bank);

	/**
	 * @param bankId
	 * @return
	 */
	public Optional<Bank> getBankDetails(Integer bankId);

}
