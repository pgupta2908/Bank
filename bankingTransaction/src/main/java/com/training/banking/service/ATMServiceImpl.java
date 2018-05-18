package com.training.banking.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.banking.model.ATM;
import com.training.banking.model.Bank;
import com.training.banking.repository.IATMRepository;
import com.training.banking.repository.IBankRepository;
import com.training.banking.wrappers.ATMWrapper;

/**
 * @author Pratyush Gupta
 *
 */
@Service
public class ATMServiceImpl implements IATMService {

	@Autowired
	IATMRepository atmRepo;

	@Autowired
	IBankRepository bankRepo;

	@Override
	public ATM createATM(ATMWrapper atmWrapper) {
		Integer bankId = atmWrapper.getBankId();
		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();

		ATM atm = atmWrapper.getAtm();
		atm.setBank(bank);

		atmRepo.save(atm);
		return atm;
	}

	@Override
	public ATM addMoneyFromBank(Integer atmId, BigDecimal amount) {
		Optional<ATM> atmPossible = atmRepo.findById(atmId);
		ATM atm = atmPossible.get();
		BigDecimal atmInitialBalance = atm.getAmount();
		BigDecimal atmUpdatedBalance = atmInitialBalance.add(amount);
		atm.setAmount(atmUpdatedBalance);

		Integer bankId = atm.getBank().getBankId();
		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();
		BigDecimal bankInitialBalance = bank.getAmount();
		BigDecimal bankUpdatedBalance = bankInitialBalance.subtract(amount);
		bank.setAmount(bankUpdatedBalance);

		atmRepo.save(atm);
		return atm;
	}

	@Override
	public ATM withdrawMoney(Integer atmId, BigDecimal amount) {
		Optional<ATM> atmPossible = atmRepo.findById(atmId);
		ATM atm = atmPossible.get();
		BigDecimal atmInitialBalance = atm.getAmount();
		BigDecimal atmUpdatedBalance = atmInitialBalance.subtract(amount);
		atm.setAmount(atmUpdatedBalance);

		Integer bankId = atm.getBank().getBankId();
		Optional<Bank> bankPossible = bankRepo.findById(bankId);
		Bank bank = bankPossible.get();
		BigDecimal bankInitialBalance = bank.getAmount();
		BigDecimal bankUpdatedBalance = bankInitialBalance.add(amount);
		bank.setAmount(bankUpdatedBalance);

		atmRepo.save(atm);
		return atm;
	}

}
