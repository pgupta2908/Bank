package com.training.audit.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AuditLog {

	@Id
	private String eventId;
	private String eventName;
	private String eventType;
	// private Timestamp eventDate;
	private Date eventDate;
	private String userId;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = UUID.randomUUID().toString();
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = new Date();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AuditLog() {
		super();
	}

	public AuditLog(String eventName, String eventType, String userId) {
		super();
		this.eventName = eventName;
		this.eventType = eventType;
		this.userId = userId;
	}

	public AuditLog(String eventId, String eventName, String eventType, Date eventDate, String userId) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = new Date();
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AuditLog [eventId=" + eventId + ", eventName=" + eventName + ", eventType=" + eventType + ", eventDate="
				+ eventDate + ", userId=" + userId + "]";
	}

	// private BaseEntity oldValue;

	// private BaseEntity newValue;

}
