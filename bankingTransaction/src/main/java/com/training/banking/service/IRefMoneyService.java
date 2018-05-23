package com.training.banking.service;

import java.util.List;

import com.training.banking.model.RefMoney;

public interface IRefMoneyService {

	public RefMoney createRefMoney(RefMoney refMoney);
	
	public List<RefMoney> listAllRefMoney();
	
}
