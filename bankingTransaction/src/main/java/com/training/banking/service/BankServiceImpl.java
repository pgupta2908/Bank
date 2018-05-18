package com.training.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.banking.model.Bank;
import com.training.banking.repository.IBankRepository;

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	IBankRepository bankRepo;

	@Override
	public Bank createBank(Bank bank) {
		return bankRepo.save(bank);
	}

	@Override
	public Optional<Bank> getBankDetails(Integer bankId) {
		return bankRepo.findById(bankId);
	}

}
