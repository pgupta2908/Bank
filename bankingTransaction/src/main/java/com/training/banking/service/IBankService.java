package com.training.banking.service;

import java.util.Optional;

import com.training.banking.model.Bank;

public interface IBankService {

	public Bank createBank(Bank bank);

	public Optional<Bank> getBankDetails(Integer bankId);

}
