package com.easemybooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.easemybooking.dto.OwnerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.Owner;
import com.easemybooking.repository.OwnerRepository;


@Service
public class OwnerServiceImpl implements OwnerService {
	
	private OwnerRepository ownerRepository;

	@Autowired
	public OwnerServiceImpl(OwnerRepository ownerrepository) {
		
		this.ownerRepository = ownerrepository;
	}
	
	@Override
	public Owner register(Owner user) throws UserAlreadyExistsException {

        Optional<Owner> isUserExists= ownerRepository.findByEmailId(user.getEmailId());

        if(isUserExists.isPresent())
        throw new UserAlreadyExistsException("User Already Exists.....");
        else
        {
          String encyrptedPassword=  BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());

          user.setPassword(encyrptedPassword);

          Owner newUser=  ownerRepository.save(user);
        return newUser;
        }

    }

	@Override
	public Owner login(OwnerDTO ownerdto ) throws UserNotFoundException {
		Owner userFound=null;
        Optional<Owner> isUserExists= ownerRepository.findByEmailId(ownerdto.getEmailId());

        if(isUserExists.isPresent())
        {
            userFound=isUserExists.get();
            //check the password encrpted password stored in the datase with the password sent throug DTO

           boolean isValid= BCrypt.checkpw(ownerdto.getPassword(),userFound.getPassword());

           if(isValid)
               return userFound;
           else
               throw new UserNotFoundException("User Not Found...");
        }
        else
            throw new UserNotFoundException("User Not Found...");
    }
    	
	}
	
	

