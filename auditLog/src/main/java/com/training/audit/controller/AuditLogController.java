package com.training.audit.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.audit.exception.CreationException;
import com.training.audit.exception.NullOrNegativeValuesException;
import com.training.audit.model.AuditLog;
import com.training.audit.service.IAuditLogService;

@RestController
@RequestMapping(value = "/auditLog")
public class AuditLogController {

	Logger log = Logger.getLogger(AuditLogController.class.getName());

	@Autowired
	IAuditLogService auditLogService;

	@PostMapping(value = "/create")
	public ResponseEntity<?> createAuditLog(@RequestBody AuditLog auditLog) {
		try {
			AuditLog createdAuditLog = auditLogService.createAuditLog(auditLog);
			return new ResponseEntity<AuditLog>(auditLog, HttpStatus.CREATED);
		}

		/*
		 * if (createdAuditLog != null) return new ResponseEntity<AuditLog>(auditLog,
		 * HttpStatus.CREATED); else return new
		 * ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		 */

		catch (NullOrNegativeValuesException e) {
			log.error("AuditLog Creation Exception " + e.getMessage());
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		} catch (CreationException e) {
			log.error("AuditLog Creation Exception " + e.getMessage());
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(value = "/viewAll")
	public ResponseEntity<List<AuditLog>> viewAllAuditLogs() {
		List<AuditLog> auditLogList = auditLogService.viewAllAuditLogs();
		if (auditLogList.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<AuditLog>>(auditLogList, HttpStatus.FOUND);
	}

	@PutMapping(value = "/update/{eventId}")
	public ResponseEntity<AuditLog> updateAuditLog(@PathVariable(value = "eventId") String eventId,
			@RequestBody AuditLog auditLog/* , @RequestParam String eventId */) {
		AuditLog updatedAuditLog = auditLogService.updateAuditLog(eventId, auditLog);
		if (updatedAuditLog != null)
			return new ResponseEntity<AuditLog>(auditLog, HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	@GetMapping(value = "/delete")
	public ResponseEntity<String> deleteAuditLog(@RequestParam String eventId) {
		String deletedEventId = auditLogService.deleteAuditLog(eventId);
		if (deletedEventId == "success")
			return new ResponseEntity<String>(deletedEventId, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
}
