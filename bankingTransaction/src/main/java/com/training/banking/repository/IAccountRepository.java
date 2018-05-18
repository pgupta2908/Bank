package com.training.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

}
