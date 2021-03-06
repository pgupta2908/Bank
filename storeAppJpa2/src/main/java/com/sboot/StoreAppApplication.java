package com.sboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sboot.model.Customer;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Author {

	String author();
}

// Applying annotation
class Hello {
	@Author(author = "Prateek")
	public void sayHello() {
		System.out.println("hello ");
	}
}

@SpringBootApplication
public class StoreAppApplication implements ApplicationRunner {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		SpringApplication.run(StoreAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>> HELLO STORE APP>>>>>>>>>>>>>>>>>>>");

		 Hello hi = new Hello();
		 hi.sayHello();

		StoreAppApplication st = new StoreAppApplication();

		 Method m = hi.getClass().getMethod("sayHello");

		 Author man = m.getAnnotation(Author.class);

		 addCustomer();

		 System.out.println("hi : " + man.author());

		System.out.println(">>>>>>>>>add customer>>>>>>>> ");
		addCustomer();
		System.out.println(">>>>>>>>>view all customer>>>>>>>> ");
		viewAllCustomer();

	}

	/*
	 * 
	 * 
	 * */
	public void addCustomer() {
		final String uri = "http://localhost:8080/customer/addcustomer";

		Customer customer = new Customer("fd", "sg", "rt", false);

		RestTemplate restTemplate = new RestTemplate();

		// CustomerServiceImpl cts = restTemplate.postForObject(uri, customer,
		// responseType, uriVariables)

		Customer result = restTemplate.postForObject(uri, customer, Customer.class);

		System.out.println(result);
	}

	/*
	 * 
	 * */
	public void viewAllCustomer() {

		final String uri = "http://localhost:8080/customer/viewcustomer";

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(uri, Customer[].class);
		// Customer result = restTemplate.getForObject(uri,Customer.class);

		int i = responseEntity.getBody().length;

		System.out.println("-witjout loop..->>>>>>" + Arrays.asList(responseEntity.getBody()));
	}

	/*
	 * 
	 * */
	public void updateCustomer(List<Customer> list) {
		final String uri = "http://localhost:8080/customer/updatecustomer";
		RestTemplate restTemplate = new RestTemplate();
		String result;

		restTemplate.put(uri, list);

	}
}
