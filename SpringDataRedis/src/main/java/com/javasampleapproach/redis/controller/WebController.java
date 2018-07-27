package com.javasampleapproach.redis.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.redis.model.Customer;
import com.javasampleapproach.redis.repo.CustomerRepository;

@RestController
public class WebController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/save")
	public String save() {
		// save a single Customer
		/*customerRepository.save(new Customer(1L, "Jack", 10, "Smith"));
		customerRepository.save(new Customer(2L, "Adam", 12, "Johnson"));
		customerRepository.save(new Customer(3L, "Kim", 11, "Smith"));
		customerRepository.save(new Customer(4L, "David", 12, "Williams"));
		customerRepository.save(new Customer(5L, "Peter", 13, "Davis"));*/

		Long id = 1L;
		/*Date start = new Date();
		System.out.println(start);*/
		long start=System.currentTimeMillis();
		for(int count=1; count<=100000; count++)
		{
			Customer customer = new Customer();
			customer.setId(id++);
			customer.setFirstName("Rohan");
			customer.setAge(12);
			customer.setEmail("hello@gmail.com");
			customerRepository.save(customer);
		}
		/*Date end = new Date();
		System.out.println(end);*/
		long end=System.currentTimeMillis();
		long result=(end-start);
		String time =  String.valueOf(result);
		System.out.println(time);
		return "Done";
	}

	@RequestMapping("/findall")
	public String findAll() {
		/*Date start = new Date();
		System.out.println(start);*/
		long start=System.currentTimeMillis();
		String result = "";
		Map<Long, Customer> customers = customerRepository.findAll();

		for (Customer customer : customers.values()) {
			result += customer.toString() + "<br>";
		}
		/*Date end = new Date();
		System.out.println(end);*/
		long end=System.currentTimeMillis();
		long results=(end-start);
		String time =  String.valueOf(results);
		System.out.println(time);
		return result;
	}

	@RequestMapping("/find")
	public String findById(@RequestParam("id") Long id) {
		long start=+System.currentTimeMillis();
		String result = "";
		result = customerRepository.find(id).toString();
		long end=System.currentTimeMillis();
		long results=(end-start);
		String time =  String.valueOf(results);
		System.out.println(time);
		return result;
	}

	/*@RequestMapping(value = "/uppercase")
	public String postCustomer(@RequestParam("id") Long id) {
		Customer customer = customerRepository.find(id);
		customer.setFirstName(customer.getFirstName().toUpperCase());
		customer.setLastName(customer.getLastName().toUpperCase());

		customerRepository.update(customer);

		return "Done";
	}*/

	@RequestMapping("/delete")
	public String deleteById(@RequestParam("id") Long id) {
		customerRepository.delete(id);

		return "Done";
	}
}
