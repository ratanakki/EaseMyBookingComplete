import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './BookingHistory.css';
 
function BookingHistory() {
  const [bookings, setBookings] = useState([]);
  const [selectedBooking, setSelectedBooking] = useState(null);
  const customeremail=localStorage.getItem("email");
 
  const staticEmail = `http://localhost:9001/api/v1/bookings/email/${customeremail}`; // Define your static email here
 
  useEffect(() => {
    axios.get(staticEmail)
      .then(response => {
        setBookings(response.data);
      })
      .catch(error => {
        console.error('Error fetching bookings:', error);
      });
  }, [staticEmail]);
 
  const openModal = (booking) => {
    setSelectedBooking(booking);
  };
 
  const closeModal = () => {
    setSelectedBooking(null);
  };
 
  return (
    <div className="container">
      <nav>
        {/* Your navigation bar content */}
      </nav>
      <div className="booking-history-container">
        <div className="booking-cards-container">
          {bookings.map(booking => (
            <div key={booking.id} className="booking-card" onClick={() => openModal(booking)}>
              <h3>Booking Details</h3>
              <p><strong>Email ID:</strong> {customeremail}</p>
              <p><strong>Place Name:</strong> {booking.placeName}</p>
              <p><strong>Name:</strong> {booking.name}</p>
              <p><strong>Number of Adults:</strong> {booking.numberOfAdults}</p>
              <p><strong>Number of Children:</strong> {booking.numberOfChildren}</p>
              <p><strong>Date of Booking:</strong> {booking.dateOfBooking}</p>
              <p><strong>Cost:</strong> {booking.cost}</p>
            </div>
          ))}
        </div>
      </div>
      <footer>
        {/* Your footer content */}
      </footer>
 
      {selectedBooking && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={closeModal}>&times;</span>
            <h2>Booking Details</h2>
            <p><strong>Email ID:</strong> {selectedBooking.emailId}</p>
            <p><strong>Place Name:</strong> {selectedBooking.placeName}</p>
            <p><strong>Name:</strong> {selectedBooking.name}</p>
            <p><strong>Number of Adults:</strong> {selectedBooking.numberOfAdults}</p>
            <p><strong>Number of Children:</strong> {selectedBooking.numberOfChildren}</p>
            <p><strong>Date of Booking:</strong> {selectedBooking.dateOfBooking}</p>
            <p><strong>Cost:</strong> {selectedBooking.cost}</p>
          </div>
        </div>
      )}
    </div>
  );
}
 
export default BookingHistory;