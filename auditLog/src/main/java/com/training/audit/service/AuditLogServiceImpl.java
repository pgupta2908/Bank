package com.training.audit.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.audit.exception.CreationException;
import com.training.audit.exception.NotFoundException;
import com.training.audit.exception.NullOrNegativeValuesException;
import com.training.audit.model.AuditLog;
import com.training.audit.repository.IAuditLogRepository;

@Service
public class AuditLogServiceImpl implements IAuditLogService {

	Logger log = Logger.getLogger(AuditLogServiceImpl.class.getName());

	@Autowired
	IAuditLogRepository auditLogRepo;

	@Autowired
	Environment env;

	@Override
	public AuditLog createAuditLog(AuditLog auditLog) throws NullOrNegativeValuesException, CreationException {
		/*
		 * try {
		 */
		log.info("creating audit log");

		// check for null value of auditLog
		if (auditLog == null) {
			log.error(env.getProperty("nullObject"));
			throw new NullOrNegativeValuesException("Please check for null values of auditLog");
		}

		// check for null or negative values of fields
		if (/* auditLog.getEventDate() == null || auditLog.getEventId() == null || */auditLog.getEventName() == null
				|| auditLog.getEventType() == null || auditLog.getUserId() == null) {
			log.error(env.getProperty("nullOrNegativeValues"));
			throw new NullOrNegativeValuesException("Please check for null values of auditLog");
		}

		// check for already existing auditLog
		if (auditLog.getEventId() != null) {
			Optional<AuditLog> auditLogPossible = auditLogRepo.findByEventId(auditLog.getEventId());
			if (auditLogPossible.isPresent()) {
				log.error(env.getProperty("alreadyExists"));
				throw new CreationException("auditLog object already exists");
			} else {
				log.info("AuditLog created successfully");
				return auditLogRepo.save(auditLog);
			}
		}
		return null;
	}

	@Override
	public List<AuditLog> viewAllAuditLogs() {
		return auditLogRepo.findAll();
	}

	@Override
	public AuditLog updateAuditLog(UUID eventId, /*AuditLog auditLog, */String userId) {
		try {
			/*// check for null value of auditLog
			if (auditLog == null) {
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}*/

			/*// check for null or negative values of fields
			if ( auditLog.getEventDate() == null || auditLog.getEventId() == null || auditLog.getEventName() == null
					|| auditLog.getEventType() == null || auditLog.getUserId() == null) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}*/

			Optional<AuditLog> auditLogPossible = auditLogRepo.findByEventId(eventId);
			boolean auditLogPresence = auditLogPossible.isPresent();

			// check for already existing auditLog
			// if (auditLog.getEventId() != null) {
			if (auditLogPresence == false) {
				log.error(env.getProperty("nullObject"));
				throw new NotFoundException("auditLog does not exist");
			} else {
				AuditLog updatedAuditLog = auditLogPossible.get();

				/*String eventName = updatedAuditLog.getEventName();
				String eventType = updatedAuditLog.getEventType();
				String eventDate = updatedAuditLog.getEventDate();
				String userId = updatedAuditLog.getUserId();
				Object oldObject = updatedAuditLog.getOldObject();
				Object newObject = updatedAuditLog.getNewObject();

				updatedAuditLog.setEventName(eventName);
				updatedAuditLog.setEventType(eventType);
				updatedAuditLog.setEventDate(eventDate);
				updatedAuditLog.setUserId(userId);
				updatedAuditLog.setOldObject(oldObject);
				updatedAuditLog.setNewObject(newObject);*/
				
				updatedAuditLog.setUserId(userId);
				
				log.info("AuditLog updated successfully");
				return auditLogRepo.save(updatedAuditLog);
			}
			// }

			/*
			 * log.error(env.getProperty("nullObject")); throw new
			 * NotFoundException("auditLog does not exist");
			 */

		/*} catch (NullOrNegativeValuesException e) {
			log.error("AuditLog Updation Exception " + e.getMessage());
			// return null;
*/		} catch (NotFoundException e) {
			log.error("AuditLog Updation Exception " + e.getMessage());
			// return null;
		}
		return null;
	}

	@Override
	public String deleteAuditLog(UUID eventId) {
		try {
			// check for negative or null value of eventId
			if (eventId.equals(null)) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException("Please check for null values of auditLog");
			}

			// if (eventId != null) {

			Optional<AuditLog> auditLogPossible = auditLogRepo.findByEventId(eventId);
			boolean auditLogPresence = auditLogPossible.isPresent();

			// check for presence of auditLog
			if (auditLogPresence == false) {
				log.error(env.getProperty("nullObject"));
				throw new NotFoundException("auditLog does not exist");
			}
			// }

			else {
				auditLogRepo.deleteById(eventId);

				/*
				 * Optional<AuditLog> auditLogPossible = auditLogRepo.findById(eventId);
				 * if(auditLogPossible != null) { AuditLog auditLog = auditLogPossible.get();
				 * log.error(env.getProperty("alreadyExists")); throw new
				 * CreationException("auditLog object already exists"); } else {
				 */
				log.info("delete successful");
				return "success";
				// }
			}

		} catch (NotFoundException e) {
			log.error("AuditLog Deletion Exception " + e.getMessage());
			// return null;
		} catch (NullOrNegativeValuesException e) {
			log.error("AuditLog Deletion Exception " + e.getMessage());
			// return null;
		} /*
			 * catch (CreationException e) { log.error("AuditLog Deletion Exception " +
			 * e.getMessage()); // return null; }
			 */
		return null;
	}

}
