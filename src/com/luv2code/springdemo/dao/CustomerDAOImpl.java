package com.luv2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.luv2code.springdemo.entity.Customer;

@Repository 
public class CustomerDAOImpl implements CustomerDAO {
	
	//need to inject session factory
		
	@Autowired
	private SessionFactory sessionFactory;

	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> theQuery =  currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public Customer getCustomer(int id) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
					
		//execute query and get result list
		Customer customer = currentSession.get(Customer.class, id);
		//return the results
		return customer;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//get the current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update customer
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}

	@Override
	public List<Customer> search(String theSearchName) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = null;
		
		if(theSearchName != null && theSearchName.trim().length() > 0) {
			query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			//search box empty return all customers
			query = session.createQuery("from Customer", Customer.class);
		}
		
		return query.getResultList();
	}

}
