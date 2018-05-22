package com.training.banking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class RefMoney {

	@ElementCollection
	private List<Integer> denominationList = new ArrayList<Integer>();

	public List<Integer> getDenominationList() {
		return denominationList;
	}

	public void setDenominationList(List<Integer> denominationList) {
		this.denominationList = denominationList;
	}

	public RefMoney() {
		super();
	}

	public RefMoney(List<Integer> denominationList) {
		super();
		this.denominationList = denominationList;
	}

	@Override
	public String toString() {
		return "RefMoney [denominationList=" + denominationList + "]";
	}

}
