package com.sboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer customerId;
	
	String customerName;
	String customerAddress;
	String paymentMode;
	
	Boolean operation;

	Customer()
	{
		
	}
	
	public Customer(String name, String address, String payMode, Boolean oper)
	{
		customerName=name;
		customerAddress=address;
		paymentMode=payMode;
		operation=oper;
	}
	
	public Integer  getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer  customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public Boolean getOperation() {
		return operation;
	}

	public void setOperation(Boolean operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", paymentMode=" + paymentMode + ", operation=" + operation + "]";
	}
	
	
}
