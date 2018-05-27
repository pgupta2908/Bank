/*package com.training.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "atm_denomination")
public class ATMDenomination {

	@Id
	@NotNull
	// @OneToOne(targetEntity = RefMoney.class)
	private Integer denomination;

	@Column(name = "no_of_denomination")
	private Integer noOfDenomination;

	@OneToOne(targetEntity = ATM.class)
	private ATM atm;

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

	public ATM getAtm() {
		return atm;
	}

	public void setAtm(ATM atm) {
		this.atm = atm;
	}

	public ATMDenomination() {
		super();
	}

	public ATMDenomination(@NotNull Integer denomination, Integer noOfDenomination, ATM atm) {
		super();
		this.denomination = denomination;
		this.noOfDenomination = noOfDenomination;
		this.atm = atm;
	}

	@Override
	public String toString() {
		return "ATMDenomination [denomination=" + denomination + ", noOfDenomination=" + noOfDenomination + ", atm="
				+ atm + "]";
	}

}
*/