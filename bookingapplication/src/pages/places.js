import React, { useState, useEffect } from 'react';
import axios from 'axios';

import './Places.css';
import Modal from './Modal';

import { IonIcon } from '@ionic/react';
import { location } from 'ionicons/icons';
import { toast } from 'react-toastify';



function Places() {
  const [places, setPlaces] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedDescription, setSelectedDescription] = useState(null);
  const [selectedPlace, setSelectedPlace] = useState(null);
  const [bookingDetails, setBookingDetails] = useState({
    emailID: '',
    name: '',
    numberOfAdults: 0,
    numberOfChildren: 0,
    ownerEmailId: ''  // New field to store ownerEmailId
  });
  const [cost, setCost] = useState(0);
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
  const [loginEmail, setLoginEmail] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [loginErrors, setLoginErrors] = useState({ email: '', password: '' });
  const [selectedPlaceForBooking, setSelectedPlaceForBooking] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    fetchPlaces();
  }, []);

  const fetchPlaces = (name = '') => {
    axios.get(`http://localhost:9007/api/v1/user/places?name=${name}`)
      .then(response => {
        const placesData = response.data.map(place => ({
          ...place,
          image: `data:image/jpeg;base64,${place.img}`
        }));
        setPlaces(placesData);
      })
      .catch(error => {
        console.error('There was an error fetching the places data!', error);
      });
  };

  const validateLoginForm = () => {
    const errors = {};
    if (!loginEmail) {
      errors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(loginEmail)) {
      errors.email = 'Email address is invalid';
    }

    if (!loginPassword) {
      errors.password = 'Password is required';
    }

    setLoginErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    fetchPlaces(searchTerm);
  };

  const toggleDescription = (index) => {
    setSelectedDescription(selectedDescription === index ? null : index);
  };

  const handleMapButtonClick = (url) => {
    window.open(url, '_blank');
  };

  const handleBookButtonClick = (place) => {
    const token = localStorage.getItem('token');
    
    if (!token) {
      setIsLoginModalOpen(true); // Open login modal if token is not present
      setSelectedPlaceForBooking(place);
    } else {
      setSelectedPlace(place);
      setBookingDetails({
        ...bookingDetails,
        ownerEmailId: place.ownerEmailId
      });
    }
  };

  const handleBookingInputChange = (e) => {
    const { name, value } = e.target;
    const updatedBookingDetails = { ...bookingDetails, [name]: value };
    setBookingDetails(updatedBookingDetails);
    calculateCost(updatedBookingDetails);
  };

  const calculateCost = ({ numberOfAdults, numberOfChildren }) => {
    const cost = (numberOfAdults * 60) + (numberOfChildren * 20);
    setCost(cost);
  };

  const handlePayment = (e) => {
    // Handle payment logic here
    // For now, just simulate a successful payment and booking submission

    e.preventDefault();

    if (!bookingDetails.emailID || !bookingDetails.name || !bookingDetails.numberOfAdults || !bookingDetails.numberOfChildren) {
      alert('Please fill in all the booking details');
      return;
    }
    const options = {
      key: 'rzp_test_7wEBjJU5Nvy4ee',
      key_secret: 'qeraLQFC2h6wJVIwK4H1qTAs',
      amount: cost * 100,
      currency: 'INR',
      name: 'STARTUP_PROJECTS',
      description: 'for testing purpose',
      handler: function (response) {
        toast.success(`Payment successful! Payment ID: ${response.razorpay_payment_id}`);
        
        const bookingData = {
          ...bookingDetails,
          placeName: selectedPlace.placeName,
          dateOfBooking: new Date().toISOString().split('T')[0],
          cost
        };

        axios.post('http://localhost:9001/api/v1/bookings/save', bookingData)
          .then(response => {
            console.log('Booking successful:', response.data);
            setSelectedPlace(null);
            toast.success('Booking successful!');
          })
          .catch(error => {
            console.error('There was an error creating the booking!', error);
            toast.warn('Booking failed. Please try again.');
          });
      },
      prefill: {
        name: bookingDetails.name,
        email: bookingDetails.emailID,
        contact: '7904425033'
      },
      notes: {
        address: 'Razorpay Corporate office'
      },
      theme: {
        color: '#3399cc'
      }
    };
    const pay = new window.Razorpay(options);
    pay.open();
  };

  const handleLoginSubmit =(e) => {
    e.preventDefault();
    if (validateLoginForm()) {
      const formData = {
        emailId: loginEmail,
        password: loginPassword
      };
      axios.post('http://localhost:9006/api/v1/users/customerlogin', formData)
        .then((response) => {
          console.log(response.data);
          alert("Logged in succesfully");
          localStorage.setItem("email",formData.emailId);
          localStorage.setItem("token",response.data.token);
          // Navigate to customer dashboard
          setIsLoggedIn(true);
          // Close login modal
          setIsLoginModalOpen(false);
          // Open booking modal
          setSelectedPlace(selectedPlaceForBooking); 
          setBookingDetails({
            ...bookingDetails,
            ownerEmailId: selectedPlaceForBooking.ownerEmailId
          });
        })
        .catch((error) => {
          if(error.response.status===404){
            alert("customer email/password is incorrect");
          }
         
          console.error(error);
        });
    }

    e.preventDefault();
  };

  // const handleRegisterLink = () => {
  //   // Implement redirection to registration page or modal
  // };

  return (
    <div className="places-page">
      <h1>Places</h1>
      <form className="search-form" onSubmit={handleSearchSubmit}>
        <input
          type="text"
          value={searchTerm}
          onChange={handleSearchChange}
          placeholder="Search by place name"
        />
        <button type="submit">Search</button>
      </form>
      <div className="places-container">
        {places.map((place, index) => (
          <div className="place-tile" key={index}>
            <div className="place-image-container">
              <img src={place.image} alt={place.placeName} className="place-image" />
            </div>
            <h2>{place.placeName}</h2>
            <p><strong>Location:</strong> {place.location}</p>
            {selectedDescription === index && <p className="place-description">{place.description}</p>}
            <button onClick={() => toggleDescription(index)}>
              {selectedDescription === index ? 'Hide Description' : 'Show Description'}
            </button>
            <button onClick={() => handleMapButtonClick(place.map)}>
            <IonIcon icon={location} /> 
            </button>
            <button onClick={() => handleBookButtonClick(place)}>
              Book</button>
          </div>
        ))}
      </div>

      {selectedPlace && (
        <div className="booking-modal">
          <div className="booking-modal-content">
            <h2>Book {selectedPlace.placeName}</h2>
            <form onSubmit={handlePayment}>
              <label>
                Email ID:
                <input
                  type="email"
                  name="emailID"
                  value={bookingDetails.emailID}
                  onChange={handleBookingInputChange}
                  required
                />
              </label>
              <label>
                Name:
                <input
                  type="text"
                  name="name"
                  value={bookingDetails.name}
                  onChange={handleBookingInputChange}
                  required
                />
              </label>
              <label>
                Number of Adults:
                <input
                  type="number"
                  name="numberOfAdults"
                  value={bookingDetails.numberOfAdults}
                  onChange={handleBookingInputChange}
                  required
                />
              </label>
              <label>
                Number of Children:
                <input
                  type="number"
                  name="numberOfChildren"
                  value={bookingDetails.numberOfChildren}
                  onChange={handleBookingInputChange}
                  
                />
              </label>
              <p><strong>Cost:</strong> Rs{cost}</p>
              <button type="button" onClick={handlePayment}>Pay</button>
              <button type="button" onClick={() => setSelectedPlace(null)}>Cancel</button>
            </form>
          </div>
        </div>
      )}

      {isLoginModalOpen && (
     <Modal isOpen={isLoginModalOpen} onClose={() => setIsLoginModalOpen(false)}>
     <h2>Login</h2>
     <form className="login-form" onSubmit={handleLoginSubmit}>
       <label htmlFor="login-email">Email:</label>
       <input
         type="email"
         id="login-email"
         value={loginEmail}
         onChange={(e) => setLoginEmail(e.target.value)}
         required
       />
       {loginErrors.email && <p className="error">{loginErrors.email}</p>}
       <label htmlFor="login-password">Password:</label>
       <input
         type="password"
         id="login-password"
         value={loginPassword}
         onChange={(e) => setLoginPassword(e.target.value)}
         required
       />
       {loginErrors.password && <p className="error">{loginErrors.password}</p>}
       <button type="submit">Login</button>
     </form>
    
   </Modal>
    )}
    </div>
  );
}

export default Places;
