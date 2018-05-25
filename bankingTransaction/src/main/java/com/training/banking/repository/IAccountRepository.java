package com.training.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Account;

/**
 * @author Pratyush Gupta
 *
 */
@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer> {

	public Optional<Account> findByAccountId(Integer id);
}
