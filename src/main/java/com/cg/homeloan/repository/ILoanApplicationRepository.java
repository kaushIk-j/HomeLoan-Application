package com.cg.homeloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LoanApplication;

@Repository
public interface ILoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
	
	public List<LoanApplication> findByCustomer(Customer customer);
	public List<LoanApplication> findAllByStatus(String status);

}