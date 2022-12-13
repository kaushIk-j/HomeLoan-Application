package com.cg.homeloan.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LoanAgreement;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.User;
import com.cg.homeloan.exception.CustomerNotFoundException;
import com.cg.homeloan.repository.ICustomerRepository;
import com.cg.homeloan.repository.ILoanAgreementRepository;
import com.cg.homeloan.repository.ILoanApplicationRepository;



/*Customer Service
 * ICustomerServiceImpl implements the interface ICustomerService
 * Customer addCustomer(Customer customer) to add new customer to table
 * Customer updateCustomer(Customer customer) throws CustomerNotFoundException to update customer details
 * Customer deleteCustomer(int custid) throws CustomerNotFoundException to delete customer using Id
 * Customer viewCustomer(int custid) throws CustomerNotFoundException view customer by Id
 * List<Customer> viewAllCustomers() to view all the customers in the table
 * List<Customer> viewCustomerList(LocalDate dateOfApplication) view customer details having loan agreement as per loan application date
 * 
 * Author : 
 * */

@Service
public class ICustomerServiceImpl implements ICustomerService {
	Logger logger = Logger.getLogger(ICustomerServiceImpl.class.getName());

	@Autowired
	ICustomerRepository repository;
	@Autowired
	ILoanAgreementRepository agreementRepository;
	@Autowired
	ILoanApplicationRepository applicationRepository;

	public ICustomerServiceImpl(ICustomerRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional
	@Override
	public Customer addCustomer(Customer customer) {

		try {
			repository.save(customer);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return customer;
	}

	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {

		Optional<Customer> optional = repository.findById(customer.getUserId().getUserId());
		if (optional.isPresent()) {
			repository.save(customer);
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer couldn't be Updated! ");
		}

	}

	@Transactional
	@Override
	public Customer deleteCustomer(int custid) throws CustomerNotFoundException {

		Optional<Customer> optional = repository.findById(custid);
		if (optional.isPresent()) {
			repository.deleteById(custid);
			return optional.get();
		} else {
			throw new CustomerNotFoundException("Customer not found for delete operation!");
		}

	}

	@Override
	public Customer viewCustomer(int custid) throws CustomerNotFoundException {

		Optional<Customer> optional = repository.findById(custid);
		try {

			if (optional.isPresent()) {
				repository.findById(custid);
			} else {
				throw new CustomerNotFoundException("Customer not found with the matching ID!");
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new CustomerNotFoundException("Customer not found with the matching ID!");
		}
		return optional.get();
	}

	@Override
	public List<Customer> viewAllCustomers() {
		List<Customer> customerList = null;
		try {
			customerList = repository.findAll();
		} catch (Exception e) {
			logger.info(e.getMessage());

		}

		return customerList;
	}

	@Override
	public List<Customer> viewCustomerList(LocalDate dateOfApplication) {
		List<Customer> customerList = new ArrayList<>();
		List<LoanAgreement> allAgreement = null;
		try {
			allAgreement = agreementRepository.findAll();
			for (LoanAgreement la : allAgreement) {
				LoanApplication loanApplication = la.getLoanApplication();

				Optional<LoanApplication> optional = applicationRepository.findById(loanApplication.getApplicationId());

				if (optional.isPresent() && optional.get().getApplicationDate().equals(dateOfApplication)) {

					customerList.add(optional.get().getCustomer());

				}

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return customerList;
	}

	@Override
	public Customer getCustomerByUser(User user) throws CustomerNotFoundException {

		Customer customer = repository.findByUser(user);
		return customer;
	}

}
