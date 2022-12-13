package com.cg.homeloan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.homeloan.entities.LoanAgreement;
import com.cg.homeloan.repository.ILoanAgreementRepository;

@Service
public class IloanAgreementServiceImpl implements IloanAgreementService {
	
	@Autowired
	ILoanAgreementRepository repository;
	
	@Override
	public LoanAgreement addNewLoanAgreement(LoanAgreement loanAggrement) {
		LoanAgreement newLoanAgreement = repository.save(loanAggrement); 
		if(newLoanAgreement != null) {
			return newLoanAgreement;
		}
		return null;
	}

}
 