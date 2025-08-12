package com.easemybooking.bookingdetails.service;

import com.easemybooking.bookingdetails.model.Booking;
import com.easemybooking.bookingdetails.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        booking.setDateOfBooking(LocalDate.now()); // Set date to the present day
        booking.setCost(booking.calculateCost());
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getBookingsByEmailID(String emailID) {
        return bookingRepository.findByEmailID(emailID);
    }

    public List<Booking> getBookingsByOwnerEmailId(String ownerEmailId) {
        return bookingRepository.findByOwnerEmailId(ownerEmailId);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
