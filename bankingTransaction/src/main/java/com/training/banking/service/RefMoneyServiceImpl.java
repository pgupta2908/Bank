package com.training.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.banking.model.RefMoney;
import com.training.banking.repository.IRefMoneyRepository;

@Service
public class RefMoneyServiceImpl implements IRefMoneyService{

	@Autowired
	IRefMoneyRepository refMoneyRepo;
	
	@Override
	public RefMoney createRefMoney(RefMoney refMoney) {
		return refMoneyRepo.save(refMoney);
	}

	@Override
	public List<RefMoney> listAllRefMoney() {
		return refMoneyRepo.findAll();
	}

}
