/*package com.training.banking.service;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.banking.exception.CreationException;
import com.training.banking.exception.NotFoundException;
import com.training.banking.exception.NullOrNegativeValuesException;
import com.training.banking.model.ATM;
import com.training.banking.model.ATMDenomination;
import com.training.banking.model.Bank;
import com.training.banking.repository.IATMDenominationRepository;
import com.training.banking.repository.IBankRepository;
import com.training.banking.wrappers.ATMDenominationWrapper;

@Service
public class ATMDenominationServiceImpl implements IATMDenominationService {

	Logger log = Logger.getLogger(ATMDenominationServiceImpl.class.getName());

	@Autowired
	IATMDenominationRepository atmDenominationRepo;
	
	@Autowired
	IATMRepository atmRepo;

	@Autowired
	Environment env;

	@Override
	public ATMDenomination createAtmDenomination(ATMDenominationWrapper atmDenominationWrapper) {
		try {

			// check for null value of atmDenominationWrapper object
			if (atmDenominationWrapper.equals(null)) {
				log.error(env.getProperty("nullObject"));
				throw new NullOrNegativeValuesException(
						"Please check for not null values of atmDenominationWrapper object");
			}

			// check for null or negative values of atmId and atmDenomination object
			if (atmDenominationWrapper.getAtmId() <= 0 || atmDenominationWrapper.getAtmDenomination().equals(null)) {
				log.error(env.getProperty("nullOrNegativeValues"));
				throw new NullOrNegativeValuesException(
						"Please check for positive value of bank id and not null value of atmDenomination");
			}

			// check for already existing atmDenomination object
			if (atmDenominationWrapper.getAtmDenomination().getDenomination() != 0) {
				Optional<ATMDenomination> atmDenominationPossible = atmDenominationRepo
						.findById(atmDenominationWrapper.getAtmDenomination().getDenomination());
				if (atmDenominationPossible.isPresent()) 
				{
					log.error(env.getProperty("alreadyExists"));
					throw new CreationException("atmDenomination already exists");
				}
			}
			
			else
			{
				Integer bankId = atmDenominationWrapper.getBankId();
				Optional<Bank> bankPossible = bankRepo.findByBankId(bankId);
				
				boolean bankPresence = bankPossible.isPresent();
				
				// check for presence of bank object
				if (bankPresence == false) {
					log.error(env.getProperty("nullObject"));
					throw new NotFoundException("bank does not exist with corresponding bank id");
				}

				// when everything is correct
				else {
					ATM atm = atmPossible.get();
					log.info("bank object is present");
					
					ATMDenomination atmDenomination = atmDenominationWrapper.getAtmDenomination();
					
					atmDenomination.setAtm(atm);
				}
			}

		} catch (NullOrNegativeValuesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ATMDenomination updateAtmDenomination(Integer denomination, Integer noOfDenomination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ATMDenomination> viewAllDenominations() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/