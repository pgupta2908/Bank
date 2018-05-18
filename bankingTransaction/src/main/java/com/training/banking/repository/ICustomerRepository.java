package com.training.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.banking.model.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}
