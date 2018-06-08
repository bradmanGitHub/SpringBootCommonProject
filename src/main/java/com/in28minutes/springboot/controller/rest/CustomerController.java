package com.in28minutes.springboot.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.model.Customer;
import com.in28minutes.springboot.model.mapper.CustomerAddressMapper;
import com.in28minutes.springboot.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/customers")
	public List<CustomerAddressMapper> retrieveQuestionsForSurvey(){
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/customer/customers2")
	public List<Customer> retrieveQuestionsForSurvey2(){
		return customerService.getAllCustomer2();
	}
	
	@GetMapping("/customer/addnew")
	public void addNewCustomer(){
		customerService.addNewCustomer();
	}
	
}
