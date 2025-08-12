package com.easemybooking.service;

import com.easemybooking.dto.OwnerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.Owner;

public interface OwnerService {
	
	public Owner register(Owner user) throws UserAlreadyExistsException;
	public Owner login(OwnerDTO user) throws UserNotFoundException;

}
