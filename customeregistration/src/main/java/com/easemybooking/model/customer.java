package com.easemybooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class customer {
	
	@Id
	private String emailId;
	private String firstName;
	private String lastName;
	private String phNo;
	private String password;
	private String conPass;
	public customer(String email, String firstName, String lastName, String phNo, String password, String conPass) {
		super();
		this.emailId = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phNo = phNo;
		this.password = password;
		this.conPass = conPass;
	}
	
	
public customer(){
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String email) {
		this.emailId = email;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConPass() {
		return conPass;
	}
	public void setConPass(String conPass) {
		this.conPass = conPass;
	}
	

}
