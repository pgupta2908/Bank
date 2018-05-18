package com.training.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Integer> {

}
