package com.training.audit.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.training.audit.exception.CreationException;
import com.training.audit.exception.NotFoundException;
import com.training.audit.exception.NullOrNegativeValuesException;
import com.training.audit.model.AuditLog;
import com.training.audit.repository.IAuditLogRepository;

public class AuditLogServiceImpl implements IAuditLogService {

	Logger log = Logger.getLogger(AuditLogServiceImpl.class.getName());

	@Autowired
	IAuditLogRepository auditLogRepo;

	@Autowired
	Environment env;

	@Override
	public AuditLog createAuditLog(AuditLog auditLog) {

		try {
			log.info("creating audit log");

			// check for null value of auditLog
			if (auditLog == null) {
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}

			// check for null or negative values of fields
			if (auditLog.getEventDate() == null || auditLog.getEventId() == null || auditLog.getEventName() == null
					|| auditLog.getEventType() == null || auditLog.getUserId() == null) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}

			// check for already existing auditLog
			if (auditLog.getEventId() != null) {
				Optional<AuditLog> auditLogPossible = auditLogRepo.findById(auditLog.getEventId());
				if (auditLogPossible.isPresent()) {
					log.error(env.getProperty("alreadyExists"));
					throw new CreationException("bank object already exists");
				}
			}

			log.info("AuditLog created successfully");
			return auditLogRepo.save(auditLog);
		} catch (NullOrNegativeValuesException e) {
			log.error("AuditLog Creation Exception " + e.getMessage());
			return null;
		} catch (CreationException e) {
			log.error("AuditLog Creation Exception " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<AuditLog> viewAllAuditLogs() {
		return auditLogRepo.findAll();
	}

	@Override
	public AuditLog updateAuditLog(AuditLog auditLog, UUID eventId) {
		try {
			// check for null value of auditLog
			if (auditLog == null) {
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}

			// check for null or negative values of fields
			if (auditLog.getEventDate() == null || auditLog.getEventId() == null || auditLog.getEventName() == null
					|| auditLog.getEventType() == null || auditLog.getUserId() == null) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}

			// check for already existing auditLog
			if (auditLog.getEventId() != null) {
				Optional<AuditLog> auditLogPossible = auditLogRepo.findById(auditLog.getEventId());
				if (auditLogPossible.isPresent()) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("auditLog does not exist");
				}
			}
			log.info("AuditLog updated successfully");
			return auditLogRepo.save(auditLog);
		} catch (NullOrNegativeValuesException e) {
			log.error("AuditLog Updation Exception " + e.getMessage());
			return null;
		} catch (NotFoundException e) {
			log.error("AuditLog Updation Exception " + e.getMessage());
			return null;
		}
	}

	@Override
	public UUID deleteAuditLog(UUID eventId) {
		try {
			// check for already existing auditLog
			if (eventId != null) {
				Optional<AuditLog> auditLogPossible = auditLogRepo.findById(eventId);
				boolean auditLogPresence = auditLogPossible.isPresent();
				if (auditLogPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("auditLog does not exist");
				}
			}

			// check for negative or null value of eventId
			if(eventId.equals(null))
			{
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}
			
			Optional<AuditLog> auditLogPossible = auditLogRepo.findById(eventId);
			AuditLog auditLog = auditLogPossible.get();
			return auditLogRepo.deleteById(eventId);
		} catch (NotFoundException e) {
			log.error("AuditLog Deletion Exception " + e.getMessage());
			return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("AuditLog Deletion Exception " + e.getMessage());
			return null;
		}
	}

}
