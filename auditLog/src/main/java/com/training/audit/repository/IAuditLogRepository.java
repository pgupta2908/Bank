package com.training.audit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.training.audit.model.AuditLog;

@Repository
public interface IAuditLogRepository extends MongoRepository<AuditLog, String> {

}
