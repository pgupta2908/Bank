package com.training.banking.wrappers;

import com.training.banking.model.ATM;

/**
 * @author Pratyush Gupta
 *
 */
public class ATMWrapper {

	private int bankId;
	private ATM atm;

	/**
	 * @return
	 */
	public int getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 */
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return
	 */
	public ATM getAtm() {
		return atm;
	}

	/**
	 * @param atm
	 */
	public void setAtm(ATM atm) {
		this.atm = atm;
	}

	public ATMWrapper() {
		super();
	}

	/**
	 * @param bankId
	 * @param atm
	 */
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
