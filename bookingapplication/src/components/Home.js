
import React, { useState } from 'react';
import BookingHistory from './BookingHistory';
import Profile from './Profile';
import './Home.css';
import { Link} from '@reach/router';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
function Home() {
  const [activeSection, setActiveSection] = useState(null);
 
 

  const customeremail=localStorage.getItem("email");
  const handleSectionClick = (sectionId) => {
    setActiveSection(sectionId);
  };
 
  const navigate=useNavigate();
  const handleLogout = () => {
   
    // Handle the logout logic here
    console.log("Logged out");
   // toast.success("User logged out");
   navigate('/')
    localStorage.clear();
    
  };

  const handleBookingClick = (section) => {
    // Handle other section clicks if needed
    if (section === 'book-now') {
      // Navigate to the Places.js page
      // Example path: '/places'
      // Adjust the path according to your routing setup
      navigate('/places');
    }
  };
 
  return (
    <div className="home-container">
      {/* Header section with logo */}
      <header className="header">
        <img src="/Logo.jpg" alt="EasemyBooking Logo" className="logo" />
        <h1>My Dashboard</h1>
       
      </header>
 
      <nav className="side-menu">
        <ul>
          <li onClick={() => handleSectionClick("bookings")} className={activeSection === "bookings" ? "active" : ""}>
            <a href="#bookings">Booking History</a>
          </li>
          <li onClick={() => handleSectionClick("profile")} className={activeSection === "profile" ? "active" : ""}>
            <a href="#profile">Profile</a>
          </li>
          <li onClick={() => handleBookingClick("book-now")} className={activeSection === "book-now" ? "active" : ""}>
          <Link to="/places" href="#book-now">Book Now</Link>
          </li>
          <li onClick={() => handleSectionClick("help")} className={activeSection === "help" ? "active" : ""}>
            <a href="#help">Help</a>
          </li>
        </ul>
        <button className="log-out" onClick={handleLogout}>Log Out</button>
      </nav>
 
      <div className="container">
        {activeSection === "bookings" && <BookingHistory />}
        {activeSection === "profile" && <Profile />}
        {activeSection === "help" && <Help />}
        {/* Render other components based on activeSection */}
      </div>
      <footer className="footer">
        <div className="footer-content">
          <p>&copy; EasemyBooking @{new Date().getFullYear()}</p>
        </div>
      </footer>
    </div>
  );
}
 
function Help() {
  return (
    <div className="help-container">
      <h2 className="help-heading">Reach Out To Us</h2>
      <p className="help-content">
      <br/>
          Email: easemybooking@gmail.com<br/>
          <br/>
          phoneNumber: 9876543211</p>
    </div>
  );
}
 
export default Home;
 