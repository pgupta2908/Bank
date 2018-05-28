package com.training.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class BankingTransactionApplication {
	/*
	 * @Autowired private IBankRepository bankRepository;
	 */

	public static void main(String[] args) {
		SpringApplication.run(BankingTransactionApplication.class, args);
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");
	}

	/*
	 * @Bean ApplicationRunner applicationRunner() { return (args) -> {
	 * bankRepository.findAll().forEach(System.out::println); }; }
	 */
}
