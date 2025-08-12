package com.easemybooking.service;

import com.easemybooking.dto.customerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.customer;

public interface customerService {
	
	public customer register(customer user) throws UserAlreadyExistsException;
	public customer login(customerDTO user) throws UserNotFoundException;
	customer getCustomerByEmailId(String emailId) throws UserNotFoundException;
    customerDTO updateCustomer(String emailId, customer customerDetails) throws UserNotFoundException;
}
