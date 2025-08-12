import React, { useState } from 'react';
import axios from 'axios';
import "./Register.css";
import { useNavigate } from 'react-router-dom';

const Register = () => {
  const [formData, setFormData] = useState({
    placeName: '',
    description: '',
    location: '',
    map: '',
    ownerEmail: '',
    img: null,
  });
  const owneremail = localStorage.getItem("Owneremail");
  const [errors, setErrors] = useState({});
  const [openDialog, setOpenDialog] = useState(false);
  const [imagePreview, setImagePreview] = useState(null);
  const [snackbarOpen, setSnackbarOpen] = useState(false);

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    setFormData({
      ...formData,
      img: file,
    });

    const reader = new FileReader();
    reader.onloadend = () => {
      setImagePreview(reader.result);
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  };

  const validate = () => {
    const newErrors = {};
    if (!formData.placeName) newErrors.placeName = 'Place name is required';
    if (!formData.description) newErrors.description = 'Description is required';
    if (!formData.location) newErrors.location = 'Location is required';
    if (!formData.map) newErrors.map = 'Map is required';
    // if (!formData.ownerEmail) newErrors.ownerEmail = 'Owner email is required';
    if (!formData.img) newErrors.img = 'Image is required';
    return newErrors;
  };

  const registerPlace = async () => {
    try {
      const data = new FormData();
      data.append('placeName', formData.placeName);
      data.append('description', formData.description);
      data.append('location', formData.location);
      data.append('map', formData.map);
      data.append('ownerEmailId', owneremail);
      if (formData.img) {
        data.append('img', formData.img);
      }

      const response = await axios.post('http://localhost:9007/api/v1/users/places', data, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      setOpenDialog(true);
      setSnackbarOpen(true);
      setFormData({
        placeName: '',
        description: '',
        location: '',
        map: '',
        ownerEmailId: owneremail,
        img: null,
      });
      setImagePreview(null);
    } catch (error) {
      console.error('Place registration failed. Please try again.', error.response?.data?.message || error.message);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
    } else {
      setErrors({});
      registerPlace();
    }
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
  };

  const handleSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarOpen(false);
  };

  const handleBackClick = () => {
    navigate('/ownerdashboard');
  };

  return (
    <div className="register-container">
        <div className="back-button" onClick={handleBackClick}>
        <svg width="24" height="24" viewBox="0 0 24 24">
          <path fill="currentColor" d="M15.41 7.41L14 6l-6 6 6 6 1.41-1.41L10.83 12z" />
        </svg>
        <span></span>
      </div>
      <h1>Register a Place</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="placeName">Name of Place:</label>
          <input
            type="text"
            id="placeName"
            name="placeName"
            value={formData.placeName}
            onChange={handleChange}
          />
          {errors.placeName && <p>{errors.placeName}</p>}
        </div>
        <div>
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            name="description"
            value={formData.description}
            onChange={handleChange}
          ></textarea>
          {errors.description && <p>{errors.description}</p>}
        </div>
        <div>
          <label htmlFor="location">Location:</label>
          <input
            type="text"
            id="location"
            name="location"
            value={formData.location}
            onChange={handleChange}
          />
          {errors.location && <p>{errors.location}</p>}
        </div>
        <div>
          <label htmlFor="map">Map:</label>
          <input
            type="text"
            id="map"
            name="map"
            value={formData.map}
            onChange={handleChange}
          />
          {errors.map && <p>{errors.map}</p>}
        </div>
        <div>
          <label htmlFor="ownerEmail">Owner Email:</label>
          <input
            type="email"
            id="ownerEmail"
            name="ownerEmail"
            value={formData.ownerEmail}
            onChange={handleChange}
          />
          {errors.ownerEmail && <p>{errors.ownerEmail}</p>}
        </div>
        <div>
          <label htmlFor="img">Upload Image:</label>
          <input
            type="file"
            id="img"
            name="img"
            accept=".jpg, .jpeg, .png"
            onChange={handleImageChange}
          />
          {errors.img && <p>{errors.img}</p>}
        </div>
        {imagePreview && (
           <div className="image-preview-container">
            <p>Image Preview:</p>
            <img src={imagePreview} alt="Image Preview" />
          </div>
        )}
        <button type="submit">Register</button>
      </form>
      {openDialog && (
        <div className="dialog-container">
          <div className="dialog">
            <h2>Registration Successful</h2>
            <p>Registered successfully!</p>
            <button onClick={handleCloseDialog}>Close</button>
          </div>
        </div>
      )}
      {snackbarOpen && (
        <div className="snackbar-container">
          <div className="snackbar">
            <p>Registered successfully!</p>
            <button onClick={handleSnackbarClose}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Register;
