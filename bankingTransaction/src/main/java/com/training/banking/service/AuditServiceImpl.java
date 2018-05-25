package com.training.banking.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.banking.model.AuditLog;

@Service
public class AuditServiceImpl implements IAuditService {

	@Override
	public void sendAuditLog(AuditLog auditLog) {
		final String uri = "http://localhost:8080/auditLog/create";

		final RestTemplate restTemplate = new RestTemplate();
		final AuditLog result = restTemplate.postForObject(uri, auditLog, AuditLog.class);

		System.out.println(result);
	}

}
