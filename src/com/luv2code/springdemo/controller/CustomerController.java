package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//get customers from the service
		List<Customer> customers = customerService.getCustomers();
		
		//add customers to the model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		//create model attribute to bind form data
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);	
		
		return "customer-form";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id,
									Model model) {
		//get the customer from the database
		Customer customer = customerService.getCustomer(id);
		
		//set customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		return "redirect;/customer/list";
	}
	
	
	@PostMapping("/search")
	public String search(@RequestParam("theSearchName") String theSearchName, Model model) {
		
		List<Customer> searchResults = customerService.search(theSearchName);
		
		model.addAttribute("customers", searchResults);
		
		return "list-customers";
	}
}
