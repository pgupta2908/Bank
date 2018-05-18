package com.training.banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.banking.repository.IBankRepository;

@SpringBootApplication
public class BankingTransactionApplication {
	@Autowired
	private IBankRepository bankRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankingTransactionApplication.class, args);
	}
	
	@Bean
	ApplicationRunner applicationRunner() {
		return (args) -> {
			bankRepository.findAll().forEach(System.out::println);
		};
	}
}
