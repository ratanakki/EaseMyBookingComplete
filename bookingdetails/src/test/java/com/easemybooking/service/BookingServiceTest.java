package com.easemybooking.service;
 
import com.easemybooking.bookingdetails.model.Booking;
import com.easemybooking.bookingdetails.repository.BookingRepository;
import com.easemybooking.bookingdetails.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
 
public class BookingServiceTest {
 
    @Mock
    private BookingRepository bookingRepository;
 
    @InjectMocks
    private BookingService bookingService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testCreateBooking() {
        // Create a sample booking object
        Booking booking = new Booking();
        booking.setEmailID("test@example.com");
        booking.setOwnerEmailId("owner@example.com");
 
        // Mock behavior of bookingRepository.save()
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
 
        // Call the method under test
        Booking createdBooking = bookingService.createBooking(booking);
 
        // Verify that save method of repository is called once
        verify(bookingRepository, times(1)).save(any(Booking.class));
 
        // Assertions
        assertEquals("test@example.com", createdBooking.getEmailID());
        assertEquals("owner@example.com", createdBooking.getOwnerEmailId());
    }
    @Test
    public void testGetBookingByIdNotFound() {
        // Mock behavior of bookingRepository.findById() for non-existent id
        String nonExistentId = "999";
        when(bookingRepository.findById(nonExistentId)).thenReturn(Optional.empty());
 
        // Call the method under test
        Optional<Booking> retrievedBooking = bookingService.getBookingById(nonExistentId);
 
        // Assertions
        assertFalse(retrievedBooking.isPresent());
    }
 
    @Test
    public void testDeleteBooking() {
        // Mock behavior of bookingRepository.deleteById()
        String bookingId = "1";
 
        // Call the method under test
        bookingService.deleteBooking(bookingId);
 
        // Verify that deleteById method of repository is called once with the correct id
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
 
}