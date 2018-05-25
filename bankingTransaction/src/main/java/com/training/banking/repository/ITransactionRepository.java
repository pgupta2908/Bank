package com.training.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Transaction;

/**
 * @author Pratyush Gupta
 *
 */
@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {

	public Optional<Transaction> findByTransactionId(Integer id);
}
