package com.training.audit.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.audit.model.AuditLog;

@RestController
@RequestMapping(value = "/auditLog")
public class AuditLogController {

	@PostMapping(value = "/create")
	public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog auditLog) {
		
	}
	
	@GetMapping(value="/viewAll")
	public ResponseEntity<List<AuditLog>> viewAllAuditLogs()
	{
		
	}
	
	@PutMapping(value="/update")
	public ResponseEntity<AuditLog> updateAuditLog(@RequestBody AuditLog auditLog, @RequestParam UUID eventId)
	{
		
	}
	
	@DeleteMapping(value="/delete")
	public ResponseEntity<UUID> deleteAuditLog(@RequestParam UUID eventId)
	{
		
	}
}
