package com.cg.homeloan.entities;



import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emi")
public class EMI {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EMIId")
	private long EMIId;
	
	private Date dueDate;
	private double emiAmount;
	private double loanAmount;
	private double interestAmount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="loanAgreementId", referencedColumnName = "loanAgreementId")
	private LoanAgreement loanAgreement;
	
	
	public EMI(long eMIId, Date dueDate, double emiAmount, double loanAmount, double interestAmount,
			LoanAgreement loanAgreementId) {
		super();
		EMIId = eMIId;
		this.dueDate = dueDate;
		this.emiAmount = emiAmount;
		this.loanAmount = loanAmount;
		this.interestAmount = interestAmount;
		this.loanAgreement = loanAgreementId;
	}


	public EMI() {
		// TODO Auto-generated constructor stub
	}


	public long getEMIId() {
		return EMIId;
	}


	public void setEMIId(long eMIId) {
		EMIId = eMIId;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public double getEmiAmount() {
		return emiAmount;
	}


	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}


	public double getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}


	public double getInterestAmount() {
		return interestAmount;
	}


	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}


	public LoanAgreement getLoanAgreement() {
		return loanAgreement;
	}


	public void setLoanAgreement(LoanAgreement loanAgreement) {
		this.loanAgreement = loanAgreement;
	}


	@Override
	public String toString() {
		return "EMI [EMIId=" + EMIId + ", dueDate=" + dueDate + ", emiAmount=" + emiAmount + ", loanAmount="
				+ loanAmount + ", interestAmount=" + interestAmount + ", loanAgreementId=" + loanAgreement + "]";
	}
	
	
	

}
