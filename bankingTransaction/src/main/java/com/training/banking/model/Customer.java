package com.training.banking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Pratyush Gupta
 *
 */
@Table(name = "Customer")
@Entity
public class Customer extends BaseEntity implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "name")
	private String name;

	@Column(name = "pin")
	private Integer pin;

	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	/**
	 * @return
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public Integer getPin() {
		return pin;
	}

	/**
	 * @param pin
	 */
	public void setPin(Integer pin) {
		this.pin = pin;
	}

	/**
	 * @return
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Customer() {
		super();
	}

	/**
	 * @param name
	 * @param pin
	 * @param bank
	 */
	public Customer(String name, Integer pin, Bank bank) {
		super();
		this.name = name;
		this.pin = pin;
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", pin=" + pin + ", bank=" + bank + "]";
	}

	@Override
	public Customer clone() throws CloneNotSupportedException {
		return (Customer) super.clone();
	}

}
