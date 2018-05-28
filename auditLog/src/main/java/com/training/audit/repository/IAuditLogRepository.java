package com.training.audit.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.training.audit.model.AuditLog;

@Repository
public interface IAuditLogRepository extends MongoRepository<AuditLog, UUID> {

	public Optional<AuditLog> findByEventId(UUID id);

	public void deleteByEventId(UUID id);
}
