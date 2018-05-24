package com.training.banking.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.training.banking.model.Money;
import com.training.banking.repository.IMoneyRepository;

public class MoneyServiceImpl implements IMoneyService {

	Logger log = Logger.getLogger(MoneyServiceImpl.class.getName());

	@Autowired
	IMoneyRepository moneyRepo;

	@Autowired
	Environment env;

	@Override
	public Money createMoney(Money money) {
		log.info("money created successfully");
		return moneyRepo.save(money);
	}

	@Override
	public Money addMoney(BigDecimal amount, BigDecimal denomination, Integer count) {
		/*Map<BigDecimal, Integer> moneyMap = (Map<BigDecimal, Integer>) moneyRepo
				.findAll();*/
		List<Money> moneyList = new ArrayList<>();
		moneyList = moneyRepo.findAll();
		
//		System.out.println(moneyMap);
		System.out.println(moneyList);
		
		BigDecimal amountCheck = denomination.multiply(new BigDecimal(count));
		if(amount != amountCheck)
		{
			log.error("invalid entry");
		}
		
		return null;
	}

}
