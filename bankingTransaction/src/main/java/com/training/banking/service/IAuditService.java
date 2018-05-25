package com.training.banking.service;

import com.training.banking.model.AuditLog;

public interface IAuditService {

	public void sendAuditLog(AuditLog auditLog);
}
