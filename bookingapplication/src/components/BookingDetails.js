import React, { useState } from 'react';
import axios from 'axios';
import './BookingDetails.css'; // Import the CSS file
import { useNavigate } from 'react-router-dom';

const BookingDetails = () => {
  const [ownerEmailId, setOwnerEmailId] = useState('');
  const [bookings, setBookings] = useState([]);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setOwnerEmailId(e.target.value);
  };

  const handleBackClick = () => {
   navigate('/ownerdashboard'); // Navigate back to the Owner Dashboard
  };

  const fetchBookings = async () => {
    try {
      const response = await axios.get(`http://localhost:9001/api/v1/bookings/owner/${ownerEmailId}`);
      setBookings(response.data);
      setError('');
    } catch (err) {
      setError('Failed to fetch bookings. Please check the owner email ID and try again.');
      setBookings([]);
    }
  };

  return (
    <div className="content">
      <div className="booking-box">
      <div className="back-button" onClick={handleBackClick}>
          <svg width="24" height="24" viewBox="0 0 24 24">
            <path fill="currentColor" d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z"/>
          </svg>
          <span>Back to Dashboard</span>
        </div>
        <h1>Booking Details</h1>
        <input
          type="text"
          placeholder="Enter Owner Email ID"
          value={ownerEmailId}
          onChange={handleChange}
        />
        <button
          onClick={fetchBookings}
          style={{ marginTop: '1rem' }}
        >
          Fetch Bookings
        </button>

        {error && (
          <p className="error" style={{ marginTop: '2rem' }}>
            {error}
          </p>
        )}

        {bookings.length > 0 && (
          <div style={{ marginTop: '4rem' }}>
            <h2>Bookings for {ownerEmailId}</h2>
            <table>
              <thead>
                <tr>
                  <th>Place Name</th>
                  <th>Name</th>
                  <th>Adults</th>
                  <th>Children</th>
                  <th>Date</th>
                  <th>Cost</th>
                </tr>
              </thead>
              <tbody>
                {bookings.map((booking, index) => (
                  <tr key={index}>
                    <td>{booking.placeName}</td>
                    <td>{booking.name}</td>
                    <td>{booking.numberOfAdults}</td>
                    <td>{booking.numberOfChildren}</td>
                    <td>{booking.dateOfBooking}</td>
                    <td>{booking.cost}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default BookingDetails;
