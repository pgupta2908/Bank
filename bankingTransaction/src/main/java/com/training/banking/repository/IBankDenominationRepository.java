package com.training.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Bank;

@Repository
public interface IBankDenominationRepository extends JpaRepository<Bank, Integer>{

}
