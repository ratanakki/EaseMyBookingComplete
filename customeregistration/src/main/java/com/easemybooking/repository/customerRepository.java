package com.easemybooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easemybooking.model.customer;

@Repository 
public interface customerRepository extends JpaRepository<customer, String> {

//	Optional<Owner> findByEmail(String email);

	Optional<customer> findByEmailId(String email);


}
