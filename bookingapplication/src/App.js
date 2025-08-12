import React from 'react';
import { BrowserRouter as Router, Route,  Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import Places from './pages/places';

import Home from './components/Home';
import Sidebar from './components/Sidebar';
import BookingDetails from './components/BookingDetails';
import Register from './components/Register';
import OwnerDashboard from './components/Ownerdashboard';


function App() {
  return (
    <Router>
      <Routes>
        <Route  path="/" element={<HomePage />} />
        <Route path="/places" element={<Places />} />
        <Route path="/Home" element={<Home/>} />
        <Route path="/ownerdashboard" element={<OwnerDashboard/>} /> 
        <Route path="/sidebar" element={<Sidebar />} /> 
        <Route path="/booking" element={<BookingDetails />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default App;
