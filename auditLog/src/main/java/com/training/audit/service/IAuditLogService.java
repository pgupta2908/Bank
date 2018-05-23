package com.training.audit.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.audit.model.AuditLog;

public interface IAuditLogService {

	/**
	 * @param auditLog
	 * @return
	 */
	AuditLog createAuditLog(@RequestBody AuditLog auditLog);

	/**
	 * @return
	 */
	List<AuditLog> viewAllAuditLogs();

	/**
	 * @param auditLog
	 * @param eventId
	 * @return
	 */
	AuditLog updateAuditLog(@RequestBody AuditLog auditLog, @RequestParam UUID eventId);

	/**
	 * @param eventId
	 * @return
	 */
	UUID deleteAuditLog(@RequestParam UUID eventId);

}
