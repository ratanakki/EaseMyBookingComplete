package com.easemybooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easemybooking.model.Owner;

@Repository 
public interface OwnerRepository extends JpaRepository<Owner, String> {

//	Optional<Owner> findByEmail(String email);

	Optional<Owner> findByEmailId(String email);


}
