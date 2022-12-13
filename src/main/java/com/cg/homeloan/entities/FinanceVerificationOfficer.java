package com.cg.homeloan.entities;

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
@Table(name="finance_verification_officer")
public class FinanceVerificationOfficer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="financeVerificationOfficerId")
	private int financeVerificationOfficerId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId", referencedColumnName = "userId")
	private User user;
	
	private String finOfficerName;
	private String finOfficerContact;
	
//	public FinanceVerificationOfficer(User userId, String finOfficerName, String finOfficerContact) {
//		super();
//		this.user = userId;
//		this.finOfficerName = finOfficerName;
//		this.finOfficerContact = finOfficerContact;
//	}

	public User getUserId() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
	}

	public String getFinOfficerName() {
		return finOfficerName;
	}

	public void setFinOfficerName(String finOfficerName) {
		this.finOfficerName = finOfficerName;
	}

	public String getFinOfficerContact() {
		return finOfficerContact;
	}

	public void setFinOfficerContact(String finOfficerContact) {
		this.finOfficerContact = finOfficerContact;
	}

	@Override
	public String toString() {
		return "FinanceVerificationOfficer [userId=" + user + ", finOfficerName=" + finOfficerName
				+ ", finOfficerContact=" + finOfficerContact + "]";
	}
	
	
	
}
