package com.training.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.ATM;

/**
 * @author Pratyush Gupta
 *
 */
@Repository
public interface IATMRepository extends JpaRepository<ATM, Integer> {

	public Optional<ATM> findByAtmId(Integer id);
}
