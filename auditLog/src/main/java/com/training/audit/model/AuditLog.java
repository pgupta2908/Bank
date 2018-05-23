package com.training.audit.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AuditLog {

	@Id
	private UUID eventId;

	private String eventName;

	private String eventType;

	// private Timestamp eventDate;

	private LocalDateTime eventDate;

	private String userId;

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
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

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AuditLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuditLog(UUID eventId, String eventName, String eventType, LocalDateTime eventDate, String userId) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = eventDate;
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
