package com.cg.homeloan.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loan_agreement")
public class LoanAgreement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="loanAgreementId")
	private long loanAgreementId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loanApplicationId", referencedColumnName = "applicationId")
	private LoanApplication loanApplication;
	

	public LoanAgreement(long loanAgreementId, LoanApplication loanApplicationId) {
		super();
		this.loanAgreementId = loanAgreementId;
		this.loanApplication = loanApplicationId;
	}

	public LoanAgreement() {
		// TODO Auto-generated constructor stub
	}

	public long getLoanAgreementId() {
		return loanAgreementId;
	}

	public void setLoanAgreementId(long loanAgreementId) {
		this.loanAgreementId = loanAgreementId;
	}

	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	@Override
	public String toString() {
		return "LoanAgreement [loanAgreementId=" + loanAgreementId + ", loanApplicationId=" + loanApplication
				+  "]";
	}

}
