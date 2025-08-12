package com.easemybooking.bookingdetails.repository;

import com.easemybooking.bookingdetails.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByEmailID(String emailID);
    List<Booking> findByOwnerEmailId(String ownerEmailId); // New query method
}
