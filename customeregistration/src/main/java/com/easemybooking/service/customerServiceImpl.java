package com.easemybooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.easemybooking.dto.customerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.customer;
import com.easemybooking.repository.customerRepository;




@Service
public class customerServiceImpl implements customerService {
	
	private customerRepository customerRepository;

	@Autowired
	public customerServiceImpl(customerRepository customerrepository) {
		
		this.customerRepository = customerrepository;
	}
	
	@Override
	public customer register(customer user) throws UserAlreadyExistsException {

        Optional<customer> isUserExists= customerRepository.findByEmailId(user.getEmailId());

        if(isUserExists.isPresent())
        throw new UserAlreadyExistsException("User Already Exists.....");
        else
        {
          String encyrptedPassword=  BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());

          user.setPassword(encyrptedPassword);

          customer newUser=  customerRepository.save(user);
        return newUser;
        }

    }

	@Override
	public customer login(customerDTO customerdto ) throws UserNotFoundException {
		customer userFound=null;
        Optional<customer> isUserExists= customerRepository.findByEmailId(customerdto.getEmailId());

        if(isUserExists.isPresent())
        {
            userFound=isUserExists.get();
            //check the password encrypted password stored in the database with the password sent throug DTO

           boolean isValid= BCrypt.checkpw(customerdto.getPassword(),userFound.getPassword());

           if(isValid)
               return userFound;
           else
               throw new UserNotFoundException("User Not Found...");
        }
        else
            throw new UserNotFoundException("User Not Found...");
    }
	
	@Override
    public customer getCustomerByEmailId(String emailId) throws UserNotFoundException {
        Optional<customer> optionalCustomer = customerRepository.findByEmailId(emailId);
        if (optionalCustomer.isPresent()) {
            customer customer = optionalCustomer.get();
            return customer;
        } else {
            throw new UserNotFoundException("User not found with email ID: " + emailId);
        }
    }
 
    @Override
    public customerDTO updateCustomer(String emailId, customer customerDetails) throws UserNotFoundException {
        Optional<customer> optionalCustomer = customerRepository.findByEmailId(emailId);
        if (optionalCustomer.isPresent()) {
            customer existingCustomer = optionalCustomer.get();
            existingCustomer.setFirstName(customerDetails.getFirstName());
            existingCustomer.setLastName(customerDetails.getLastName());
            existingCustomer.setPhNo(customerDetails.getPhNo());
            customerRepository.save(existingCustomer);
            return new customerDTO(existingCustomer);
        } else {
            throw new UserNotFoundException("User not found with email ID: " + emailId);
        }
    }
	
    	
	}
	
	

