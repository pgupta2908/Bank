package com.training.banking.service;

import java.math.BigDecimal;

import com.training.banking.model.ATM;
import com.training.banking.wrappers.ATMWrapper;

public interface IATMService {

	public ATM createATM(ATMWrapper atmWrapper);

	public ATM addMoneyFromBank(Integer atmId, BigDecimal amount);

	public ATM withdrawMoney(Integer atmId, BigDecimal amount);

}
