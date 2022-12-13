package com.cg.homeloan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Status;
import com.cg.homeloan.repository.ILoanApplicationRepository;

@Service
public class ILoanApplicationServiceImpl implements ILoanApplicationService {

	@Autowired
	ILoanApplicationRepository repository;
	
	@Override
	public LoanApplication addNewLoanApplication(LoanApplication loanApplication) {
		LoanApplication newLoanApplicaton = repository.save(loanApplication); 
		if(newLoanApplicaton != null) {
			return newLoanApplicaton;
		}
		return null;
	}
	
	@Override
	public LoanApplication updateLoanApplication(LoanApplication loanApplication) {
		LoanApplication updatedLoanApplicaton = repository.save(loanApplication); 
		if(updatedLoanApplicaton != null) {
			return updatedLoanApplicaton;
		}
		return null;
	}
	
	@Override
	public List<LoanApplication> getAllByCustomer(Customer customer){
		return repository.findByCustomer(customer);
	}

	@Override
	public List<LoanApplication> getAllApplications() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<LoanApplication> getAllApplicationByStatus(Status status) {
		// TODO Auto-generated method stub
		System.out.println(status.label);
		return repository.findAllByStatus(status.label);
	}

	@Override
	public LoanApplication getApplicationById(long applicationId) {
		// TODO Auto-generated method stub
		Optional<LoanApplication> application =  repository.findById(applicationId);
		if(application.isPresent()) {
			return application.get();
		}
		return null;
	}

}
