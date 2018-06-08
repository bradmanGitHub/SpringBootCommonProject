package com.in28minutes.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.in28minutes.springboot.model.Address;
import com.in28minutes.springboot.model.Customer;
import com.in28minutes.springboot.model.mapper.CustomerAddressMapper;

@Mapper
public interface CustomerMyBatisDao {

	@Select("SELECT c.id, c.name, c.email, c.created_date as date, a.address1 , a.address2 FROM customer c left join address a on c.id = a.customer_id where c.id = #{id}")
	List<CustomerAddressMapper> findAll(int id);

	@Results({ @Result(column = "id", property = "id"),
			@Result(property = "addressList", javaType = List.class, column = "id", many = @Many(select = "getAddressList")) })
	@Select("SELECT id, name, email, created_date as date FROM customer where id = #{id}")
	//@Select("SELECT id, name, email, created_date as date FROM customer")
	List<Customer> findAll2(@Param("id") int id);

	@Select("select customer_id customerId, address1, address2 from address where customer_id = #{id}")
	List<Address> getAddressList(int id);
	
	@Insert("INSERT INTO CUSTOMER(name,email,created_date) VALUES(#{name}, #{email}, #{date})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	void insertCustomer(Customer customer);
	
	@Insert("INSERT INTO ADDRESS(customer_id,address1,address2) VALUES(#{customerId}, #{address1}, #{address2})")
	void insertAddress(Address address);
	
}
