package com.training.banking.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.training.banking.model.AuditLog;

@FeignClient("ZuulGateway")
//@FeignClient("AuditLog")
public interface IAuditService {

	@PostMapping(path = "/AuditLogging/auditLog/create")
	AuditLog sendAuditLog(AuditLog auditLog);
}
