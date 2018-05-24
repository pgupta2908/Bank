package com.training.audit.service;

import java.util.List;

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
	AuditLog updateAuditLog(String eventId, AuditLog auditLog);
//	AuditLog updateAuditLog(@RequestBody AuditLog auditLog/*, @RequestParam String eventId*/);

	/**
	 * @param eventId
	 * @return
	 */
	String deleteAuditLog(@RequestParam String eventId);

	

}
