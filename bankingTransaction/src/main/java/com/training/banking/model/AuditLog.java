package com.training.banking.model;

import java.util.Date;

public class AuditLog {

	private String eventId;
	public enum eventName{
		CUSTOMER,
		ACCOUNT,
		ATM,
		BANK
	};
	private enum eventTtype{
		CREATED,
		UPDATED
	};
	private Date eventDate;
	private String userId;
	private Object oldObject;
	private Object newObject;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Object getOldObject() {
		return oldObject;
	}
	public void setOldObject(Object oldObject) {
		this.oldObject = oldObject;
	}
	public Object getNewObject() {
		return newObject;
	}
	public void setNewObject(Object newObject) {
		this.newObject = newObject;
	}
	public AuditLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuditLog(String eventId, Date eventDate, String userId, Object oldObject, Object newObject) {
		super();
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.userId = userId;
		this.oldObject = oldObject;
		this.newObject = newObject;
	}
	@Override
	public String toString() {
		return "AuditLog [eventId=" + eventId + ", eventDate=" + eventDate + ", userId=" + userId + ", oldObject="
				+ oldObject + ", newObject=" + newObject + "]";
	}
	
}
