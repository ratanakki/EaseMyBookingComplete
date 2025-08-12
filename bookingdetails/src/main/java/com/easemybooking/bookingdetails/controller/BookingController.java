package com.easemybooking.bookingdetails.controller;

import com.easemybooking.bookingdetails.dto.BookingDTO;
import com.easemybooking.bookingdetails.model.Booking;
import com.easemybooking.bookingdetails.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings/save")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDTO bookingDTO) {
        LocalDate bookingDate = LocalDate.now(); // Automatically set to the present day
        Booking booking = new Booking(
        		bookingDTO.getId(),
            bookingDTO.getEmailID(),
            bookingDTO.getName(),
            bookingDTO.getNumberOfAdults(),
            bookingDTO.getNumberOfChildren(),
            bookingDate,
            bookingDTO.getOwnerEmailId() // Set ownerEmailId from DTO
        );
        booking.setPlaceName(bookingDTO.getPlaceName());
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping("/")
    public String getServer() {
        return "The Server is up and Running in the Port 9000....";
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bookings/email/{emailID}")
    public ResponseEntity<List<Booking>> getBookingsByEmailID(@PathVariable String emailID) {
        List<Booking> bookings = bookingService.getBookingsByEmailID(emailID);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/bookings/owner/{ownerEmailId}")
    public ResponseEntity<List<Booking>> getBookingsByOwnerEmailId(@PathVariable String ownerEmailId) {
        List<Booking> bookings = bookingService.getBookingsByOwnerEmailId(ownerEmailId);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
