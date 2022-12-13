package com.cg.homeloan.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customerId")
	private int customerId;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private User user;
	
	private String customerName;
	private String mobileNumber;
	private String emailId; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String gender;
	private String nationality;
	private String aadharNumber;
	private String panNumber;
	
	
	public Customer() {
		super();
		}


//	public Customer(User userId, String customerName, String mobileNumber, String emailId, Date dateOfBirth,
//			String gender, String nationality, String aadharNumber, String panNumber) {
//		super();
//		this.user = userId;
//		this.customerName = customerName;
//		this.mobileNumber = mobileNumber;
//		this.emailId = emailId;
//		this.dateOfBirth = dateOfBirth;
//		this.gender = gender;
//		this.nationality = nationality;
//		this.aadharNumber = aadharNumber;
//		this.panNumber = panNumber;
//	}
//

	
	
	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public User getUserId() {
		return user;
	}


	public void setUserId(User userId) {
		this.user = userId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getAadharNumber() {
		return aadharNumber;
	}


	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}


	public String getPanNumber() {
		return panNumber;
	}


	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	@Override
	public String toString() {
		return "Customer [userId=" + user + ", customerName=" + customerName + ", mobileNumber=" + mobileNumber
				+ ", emailId=" + emailId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", nationality="
				+ nationality + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + "]";
	}
	
	

}
