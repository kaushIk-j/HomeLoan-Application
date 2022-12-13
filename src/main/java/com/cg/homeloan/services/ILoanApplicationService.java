package com.cg.homeloan.services;

import java.util.List;

import com.cg.homeloan.entities.Customer;
import com.cg.homeloan.entities.LoanApplication;
import com.cg.homeloan.entities.Status;

public interface ILoanApplicationService {

	LoanApplication addNewLoanApplication(LoanApplication loanApplication);

	public List<LoanApplication> getAllByCustomer(Customer customer);

	public List<LoanApplication> getAllApplications();
	public List<LoanApplication> getAllApplicationByStatus(Status status);
	public LoanApplication getApplicationById(long applicationId);

	public LoanApplication updateLoanApplication(LoanApplication loanApplication);

}
