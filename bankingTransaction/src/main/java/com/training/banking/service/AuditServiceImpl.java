package com.training.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.banking.model.AuditLog;

@Service
public class AuditServiceImpl implements IAuditService {

/*	@Autowired
	DiscoveryClient discoveryClient;*/
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public AuditLog sendAuditLog(AuditLog auditLog) {
	
		String uri = "http://AuditLog/auditLog/create";
		AuditLog createdAudit = restTemplate.postForObject(uri, auditLog, AuditLog.class);
		return createdAudit;
			/*final String uri = "http://localhost:8085";

		final RestTemplate restTemplate = new RestTemplate();
		final AuditLog result = restTemplate.postForObject(uri, auditLog, AuditLog.class);

		System.out.println(result);
		
		List<ServiceInstance> serviceInstance=discoveryClient.getInstances("AuditLog");
		ServiceInstance instance=serviceInstance.get(0);
		RestTemplate restTemplate = new RestTemplate();
		//final String uri = "http://localhost:8282/audit/create";
		String URI = instance.getUri().toString();
		URI=URI+"/auditLog/create";
		AuditLog createdAudit = restTemplate.postForObject(URI, auditLog, AuditLog.class);

		return createdAudit;*/
		
		
		
	}

}
