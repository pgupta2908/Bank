package com.training.banking.service;

import java.math.BigDecimal;

import com.training.banking.model.Money;

public interface IMoneyService {

	public Money createMoney(Money money);

	Money addMoney(BigDecimal amount, BigDecimal denomination, Integer count);
}
