package com.cg.homeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.homeloan.entities.LoanAgreement;

@Repository
public interface ILoanAgreementRepository extends JpaRepository<LoanAgreement, Long> {
	
	/*public LoanAgreement addLoanAgreement(LoanAgreement loanAgreement);
		
	
	 * public LoanAgreement updateLoanAgreement(LoanAgreement loanAgreement); public
	 * LoanAgreement deleteLoanAgreement(long loanAgreementId); public
	 * List<LoanAgreement> retrieveAllLoanAgreement(); public LoanAgreement
	 * retrieveLoanAgreementById(long loanAgreementId);
	 */
		




}
