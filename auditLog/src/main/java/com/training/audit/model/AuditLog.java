package com.training.audit.model;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AuditLog {

	@Id
	private UUID eventId;
	private String eventName;
	private String eventType;
	private Timestamp eventDate;
	private String userId;
	private Object oldObject;
	private Object newObject;

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = UUID.randomUUID();
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

	public Timestamp getEventDate() {
		return eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
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
		this.eventId = UUID.randomUUID();
	}

	public AuditLog(String eventName, String eventType, Timestamp eventDate, String userId, Object oldObject,
			Object newObject) {
		super();
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.userId = userId;
		this.oldObject = oldObject;
		this.newObject = newObject;
	}

	public AuditLog(UUID eventId, String eventName, String eventType, Timestamp eventDate, String userId,
			Object oldObject, Object newObject) {
		super();
		this.eventId = UUID.randomUUID();
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.userId = userId;
		this.oldObject = oldObject;
		this.newObject = newObject;
	}

	@Override
	public String toString() {
		return "AuditLog [eventId=" + eventId + ", eventName=" + eventName + ", eventType=" + eventType + ", eventDate="
				+ eventDate + ", userId=" + userId + ", oldObject=" + oldObject + ", newObject=" + newObject + "]";
	}

}
