package com.training.banking.service;

import com.training.banking.model.AuditLog;

//@FeignClient("ZuulGateway")
//@FeignClient("AuditLog")
public interface IAuditService {

//	@PostMapping(path = "/auditLog/create")
	AuditLog sendAuditLog(AuditLog auditLog);
}
