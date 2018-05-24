/*package com.training.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank_denomination")
public class BankDenomination {

	@Id
	@OneToOne(targetEntity = RefMoney.class)
	private Integer denomination;

	@Column(name = "no_of_denomination")
	private Integer noOfDenomination;

	@OneToOne(targetEntity = Bank.class)
	private Bank bank;

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public Integer getNoOfDenomination() {
		return noOfDenomination;
	}

	public void setNoOfDenomination(Integer noOfDenomination) {
		this.noOfDenomination = noOfDenomination;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public BankDenomination() {
		super();
	}

	public BankDenomination(Integer denomination, Integer noOfDenomination, Bank bank) {
		super();
		this.denomination = denomination;
		this.noOfDenomination = noOfDenomination;
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "BankDenomination [denomination=" + denomination + ", noOfDenomination=" + noOfDenomination + ", bank="
				+ bank + "]";
	}

}
*/