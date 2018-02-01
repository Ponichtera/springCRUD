package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public Customer getCustomer(int id);

	void saveCustomer(Customer customer);

	public void deleteCustomer(int id);

	public List<Customer> search(String theSearchName);

}
 