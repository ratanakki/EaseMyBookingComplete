package com.easemybooking.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easemybooking.model.Owner;
import com.easemybooking.service.OwnerService;
import com.easemybooking.util.JwtUtils;
import com.easemybooking.dto.OwnerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins ="http://localhost:3000")

public class OwnerController {
	
	private OwnerService ownerService;

	@Autowired
	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}
	
	@PostMapping("/users/ownerregister")
	 public ResponseEntity<?>  register(@RequestBody  Owner user)
    {

        try {
        	Owner newUser= ownerService.register(user);

          return new ResponseEntity<Owner>(newUser, HttpStatus.CREATED); //201
        }

        catch(UserAlreadyExistsException  exe)
        {
            return new ResponseEntity<String>(exe.getMessage(), HttpStatus.CONFLICT); //409
        }
        
  

    }
	
	@PostMapping("/users/ownerlogin")
    public ResponseEntity<?>  login(@RequestBody OwnerDTO userDTO)
    {

        try {
        	Owner newUser= ownerService.login(userDTO);

            Map<String,String> tokenGenerated= JwtUtils.generateToken(userDTO.getEmailId());

            return new ResponseEntity<Map<String,String>>(tokenGenerated, HttpStatus.OK) ;//200
        }

        catch(UserNotFoundException exe)
        {
            return new ResponseEntity<String>(exe.getMessage(), HttpStatus.NOT_FOUND); //404
        }
}
}
