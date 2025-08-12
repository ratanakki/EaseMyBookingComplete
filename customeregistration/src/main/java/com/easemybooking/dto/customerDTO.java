package com.easemybooking.dto;

import com.easemybooking.model.customer;

public class customerDTO {
	
	private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    public String phNo;
   
    
    public customerDTO() {
    	
    }
    
    
	public customerDTO(String emailId, String password, String firstName, String lastName, String phNo) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phNo = phNo;
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


	public String getPhNo() {
		return phNo;
	}


	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}


	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String email) {
		this.emailId = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public customerDTO(String email, String password) {
		super();
		this.emailId = email;
		this.password = password;
	}


	public customerDTO(customer customer) {
		// TODO Auto-generated constructor stub
	}
	
    

}
