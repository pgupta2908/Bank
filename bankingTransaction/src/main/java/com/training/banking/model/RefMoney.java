/*package com.training.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ref_money")
public class RefMoney {

	@Id
	@NotNull
	@Column(name = "denomination")
	private Integer denomination;

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public RefMoney() {
		super();
	}

	public RefMoney(Integer denomination) {
		super();
		this.denomination = denomination;
	}

	@Override
	public String toString() {
		return "RefMoney [denomination=" + denomination + "]";
	}

}
*/