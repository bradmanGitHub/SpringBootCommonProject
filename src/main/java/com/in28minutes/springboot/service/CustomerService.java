package com.in28minutes.springboot.service;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.springboot.dao.CustomerJdbcTemplateDao;
import com.in28minutes.springboot.dao.CustomerMyBatisDao;
import com.in28minutes.springboot.model.Address;
import com.in28minutes.springboot.model.Customer;
import com.in28minutes.springboot.model.mapper.CustomerAddressMapper;

@Component
public class CustomerService {

	@Autowired
	DataSource dataSource;

	@Autowired
	private CustomerJdbcTemplateDao customerJdbcTemplate;

	@Autowired
	private CustomerMyBatisDao customerDao;

	// public void addCustomer(String name, String email) {
	// customerJdbcTemplate.addCustomer(name, email);
	// }

	public List<CustomerAddressMapper> getAllCustomer() {
		// List<Customer> list = customerJdbcTemplate.findAll();
		List<CustomerAddressMapper> list = customerDao.findAll(1);
		return list;
	}

	public List<Customer> getAllCustomer2() {
		// List<Customer> list = customerJdbcTemplate.findAll();
		List<Customer> list = customerDao.findAll2(1);
		return list;
	}

	@Transactional
	public void addNewCustomer() {

		Customer cust = new Customer();
		// cust.setId(id);
		cust.setEmail("hanaga@thai.com");
		cust.setName("hanaga");
		cust.setDate(new Date());
		customerDao.insertCustomer(cust);

		Address addr = new Address();
		addr.setCustomerId(cust.getId());
		addr.setAddress1("addr1");
		addr.setAddress2("addr2");
		customerDao.insertAddress(addr);

	}

}