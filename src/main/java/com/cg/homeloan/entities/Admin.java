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
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid", referencedColumnName = "userId")
	private User user;
	
	private String adminName;
	private String adminContact;

	public Admin() {
		super();
	}

	public Admin(int userId, String adminName, String adminContact) {
		super();
		this.user = user;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}

	public User getUser() {
		return this.user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

	@Override
	public String toString() {
		return "Admin [userId=" + user + ", adminName=" + adminName + ", adminContact=" + adminContact + "]";
	}
	
	

}
