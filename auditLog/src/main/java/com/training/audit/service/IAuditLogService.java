package com.training.audit.service;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.audit.exception.CreationException;
import com.training.audit.exception.NullOrNegativeValuesException;
import com.training.audit.model.AuditLog;

public interface IAuditLogService {

	/**
	 * @param auditLog
	 * @return
	 * @throws NullOrNegativeValuesException 
	 * @throws CreationException 
	 */
	AuditLog createAuditLog(@RequestBody AuditLog auditLog) throws NullOrNegativeValuesException, CreationException;

	/**
	 * @return
	 */
	List<AuditLog> viewAllAuditLogs();

	/**
	 * @param auditLog
	 * @param eventId
	 * @return
	 */
	AuditLog updateAuditLog(UUID eventId/*, AuditLog auditLog*/, String userId);
//	AuditLog updateAuditLog(@RequestBody AuditLog auditLog/*, @RequestParam String eventId*/);

	/**
	 * @param eventId
	 * @return
	 */
	String deleteAuditLog(@RequestParam UUID eventId);

	

}
