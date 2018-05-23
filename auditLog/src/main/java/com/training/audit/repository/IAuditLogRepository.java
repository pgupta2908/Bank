package com.training.audit.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.audit.model.AuditLog;

public interface IAuditLogRepository extends MongoRepository<AuditLog, UUID> {

}
