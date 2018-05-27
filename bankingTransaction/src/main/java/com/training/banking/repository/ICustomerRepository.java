package com.training.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Customer;

/**
 * @author Pratyush Gupta
 *
 */
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByCustomerId(Integer id);
}
