package com.cg.homeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.User;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByUser(User user);
	/*
	 * public Customer addCustomer(Customer customer); public Customer
	 * updateCustomer(Customer customer) throws CustomerNotFoundException; public
	 * Customer deleteCustomer(Customer customer) throws CustomerNotFoundException;
	 * public Customer viewCustomer(int custid) throws CustomerNotFoundException;
	 * public List<Customer> viewAllCustomers(); public List<Customer>
	 * viewCustomerList(LocalDate dateOfApplication); //public List<Customer>
	 * viewCustomerList(int theatreid);
	 */
}
