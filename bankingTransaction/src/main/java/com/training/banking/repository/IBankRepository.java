package com.training.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Bank;

/**
 * @author Pratyush Gupta
 *
 */
@Repository
public interface IBankRepository extends JpaRepository<Bank, Integer> {

	public Optional<Bank> findByBankId(Integer id);
}
