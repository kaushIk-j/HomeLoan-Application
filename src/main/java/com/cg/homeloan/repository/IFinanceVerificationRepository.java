package com.cg.homeloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.homeloan.entities.FinanceVerificationOfficer;


@Repository
public interface IFinanceVerificationRepository  extends JpaRepository<FinanceVerificationOfficer, Integer> {
//public void updateStatus(LoanApplication loanApplication) throws InvalidLoanApplicationException ;

}
