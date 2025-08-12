import React from 'react';

import Sidebar from './Sidebar';

import './Ownerdashboard.css';
  
const OwnerDashboard = () => {
  return (
    <>
   
   
      <div className="app-container"> 
        
        <header className="header">
          <h1>Owner Dashboard - EasemyBooking</h1>
        </header>
        <Sidebar />
        <footer className="footer">
          <p>&copy; EasemyBooking @{new Date().getFullYear()}</p>
        </footer>
      </div>
 
    </>
  );
};
 
export default OwnerDashboard;