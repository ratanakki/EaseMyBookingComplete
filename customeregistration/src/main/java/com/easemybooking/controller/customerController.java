package com.easemybooking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easemybooking.dto.customerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.customer;
import com.easemybooking.service.customerService;
import com.easemybooking.utils.JwtUtils;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins ="http://localhost:3000")
public class customerController {
    
    private customerService customerservice;

    @Autowired
    public customerController(customerService customerservice) { // Fix parameter name to match the field
        this.customerservice = customerservice;
    }
    
    @PostMapping("/users/customerRegister")
    public ResponseEntity<?> register(@RequestBody customer user) {
        try {
            customer newUser = customerservice.register(user);
            return new ResponseEntity<customer>(newUser, HttpStatus.CREATED); // 201
        } catch (UserAlreadyExistsException exe) {
            return new ResponseEntity<String>(exe.getMessage(), HttpStatus.CONFLICT); // 409
        }
    }
    
    @PostMapping("/users/customerlogin")
    public ResponseEntity<?>  login(@RequestBody customerDTO userDTO)
    {
 
        try {
        	customer newUser= customerservice.login(userDTO);
 
            Map<String,String> tokenGenerated= JwtUtils.generateToken(userDTO.getEmailId());
 
            return new ResponseEntity<Map<String,String>>(tokenGenerated, HttpStatus.OK) ;//200
        }
 
        catch(UserNotFoundException exe)
        {
            return new ResponseEntity<String>(exe.getMessage(), HttpStatus.NOT_FOUND); //404
        }
}
    
    
    @GetMapping("/{emailId}")
    public ResponseEntity<?> getCustomerByEmailId(@PathVariable String emailId) {
        try {
            customer customer = customerservice.getCustomerByEmailId(emailId);
            return ResponseEntity.ok(customer);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
 
    @PutMapping("/customer/update/{emailId}")
    public ResponseEntity<?> updateCustomer(@PathVariable String emailId, @RequestBody customer customerDetails) {
        try {
            customerDTO updatedCustomer = customerservice.updateCustomer(emailId, customerDetails);
            return ResponseEntity.ok(updatedCustomer);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
