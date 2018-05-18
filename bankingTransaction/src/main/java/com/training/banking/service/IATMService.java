package com.training.banking.service;

import java.math.BigDecimal;

import com.training.banking.model.ATM;
import com.training.banking.wrappers.ATMWrapper;

/**
 * @author Pratyush Gupta
 *
 */
public interface IATMService {

	/**
	 * @param atmWrapper
	 * @return
	 */
	public ATM createATM(ATMWrapper atmWrapper);

	/**
	 * @param atmId
	 * @param amount
	 * @return
	 */
	public ATM addMoneyFromBank(Integer atmId, BigDecimal amount);

	/**
	 * @param atmId
	 * @param amount
	 * @return
	 */
	public ATM withdrawMoney(Integer atmId, BigDecimal amount);

}
