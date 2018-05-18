package com.training.banking.wrappers;

import com.training.banking.model.ATM;

public class ATMWrapper {

	private int bankId;
	private ATM atm;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public ATM getAtm() {
		return atm;
	}

	public void setAtm(ATM atm) {
		this.atm = atm;
	}

	public ATMWrapper() {
		super();
	}

	public ATMWrapper(int bankId, ATM atm) {
		super();
		this.bankId = bankId;
		this.atm = atm;
	}

	@Override
	public String toString() {
		return "ATMWrapper [bankId=" + bankId + ", atm=" + atm + "]";
	}

}
