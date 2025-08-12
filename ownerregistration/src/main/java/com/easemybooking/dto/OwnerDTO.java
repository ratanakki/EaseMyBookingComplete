package com.easemybooking.dto;

public class OwnerDTO {
	
	private String email;
    private String password;
    public OwnerDTO() {
    	
    }
	public String getEmailId() {
		return email;
	}
	public void setEmailId(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public OwnerDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
    

}
