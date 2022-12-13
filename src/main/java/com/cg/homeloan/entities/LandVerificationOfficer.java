package com.cg.homeloan.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="land_verification_oficer")
public class LandVerificationOfficer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int landVerificationOfficerId;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private User user;
	
	private String officerName;
	private String officerContact;
	
	
//	public LandVerificationOfficer(User userId, String officerName, String officerContact) {
//		super();
//		this.user = userId;
//		this.officerName = officerName;
//		this.officerContact = officerContact;
//	}


	public User getUserId() {
		return user;
	}


	public void setUserId(User userId) {
		this.user = userId;
	}


	public String getOfficerName() {
		return officerName;
	}


	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}


	public String getOfficerContact() {
		return officerContact;
	}


	public void setOfficerContact(String officerContact) {
		this.officerContact = officerContact;
	}


	@Override
	public String toString() {
		return "LandVerificationOfficer [userId=" + user + ", officerName=" + officerName + ", officerContact="
				+ officerContact + "]";
	}
	
	
	

}
