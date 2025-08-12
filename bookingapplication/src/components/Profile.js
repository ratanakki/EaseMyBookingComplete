import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Profile.css';
import { Card, Button, Form } from 'react-bootstrap';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
 
function Profile() {
  const [profileData, setProfileData] = useState({
    email: '',
    firstName: '',
    lastName: '',
    phoneNumber: ''
  });
 
  const [editing, setEditing] = useState(false);
  const [newFirstName, setNewFirstName] = useState('');
  const [newLastName, setNewLastName] = useState('');
  const [newPhoneNumber, setNewPhoneNumber] = useState('');
  const customeremail=localStorage.getItem("email");
 
  const profileEndpoint = `http://localhost:9006/api/v1/customer/update/${customeremail}`; // Define your profile update endpoint here
 
  useEffect(() => {
    axios.get(`http://localhost:9006/api/v1/${customeremail}`)
      .then(response => {
        const data = response.data;
        setProfileData({
          email: data.emailId,
          firstName: data.firstName || 'First Name Placeholder',
          lastName: data.lastName || 'Last Name Placeholder',
          phoneNumber: data.phNo || 'Phone Number Placeholder' // Check for empty phoneNumber
        });
      })
      .catch(error => {
        console.error('Error fetching profile data:', error);
      });
  }, [profileEndpoint]);
 
  const handleEdit = () => {
    setNewFirstName(profileData.firstName);
    setNewLastName(profileData.lastName);
    setNewPhoneNumber(profileData.phoneNumber);
    setEditing(true);
  };
 
  const handleSave = () => {
    // Phone number validation
    if (newPhoneNumber.length < 10 || newPhoneNumber.length > 12) {
      toast.error("Phone number must be between 10 and 12 digits");
      return;
    }
 
    // First name validation
    const firstNamePattern = /^[a-zA-Z]+$/;
    if (!firstNamePattern.test(newFirstName)) {
      toast.error("First name can only contain alphabetic characters");
      return;
    }
 
    // Last name validation
    const lastNamePattern = /^[a-zA-Z]+$/;
    if (!lastNamePattern.test(newLastName)) {
      toast.error("Last name can only contain alphabetic characters");
      return;
    }
 
    const updatedProfile = {
      firstName: newFirstName,
      lastName: newLastName,
      phNo: newPhoneNumber
    };
 
    // Update profile in the database
    axios.put(profileEndpoint, updatedProfile)
      .then(response => {
        console.log('Profile updated successfully:', response.data);
        // Update profile data in the state
        setProfileData({
          ...profileData,
          firstName: newFirstName,
          lastName: newLastName,
          phoneNumber: newPhoneNumber
        });
        setEditing(false);
        // Show success toast
        toast.success("Updated successfully");
      })
      .catch(error => {
        console.error('Error updating profile:', error);
        // Show error toast
        toast.error("Failed to update profile");
      });
  };
 
  return (
    <div className="profile-container">
      <Card style={{ width: '18rem' }}>
        <Card.Img variant="top" src="/profile.jpg" className="profile-image" />
        <Card.Body>
          {editing ? (
            <Form className="edit-form">
              <Form.Group controlId="formFirstName">
                <Form.Label><strong>First Name</strong></Form.Label>
                <Form.Control type="text" value={newFirstName} onChange={(e) => setNewFirstName(e.target.value)} />
              </Form.Group>
              <br />
              <Form.Group controlId="formLastName">
                <Form.Label><strong>Last Name</strong></Form.Label>
                <Form.Control type="text" value={newLastName} onChange={(e) => setNewLastName(e.target.value)} />
              </Form.Group>
              <br />
              <Form.Group controlId="formPhoneNumber">
                <Form.Label><strong>Phone Number</strong></Form.Label>
                <Form.Control type="text" value={newPhoneNumber} onChange={(e) => setNewPhoneNumber(e.target.value)} />
              </Form.Group>
              <br />
              <Button id="saveButton" className="button" onClick={handleSave}>Save</Button>
            </Form>
          ) : (
            <Card.Text className="profile-details">
              <p><strong>Email:</strong> {profileData.email}</p>
              <p><strong>First Name:</strong> {profileData.firstName}</p>
              <p><strong>Last Name:</strong> {profileData.lastName}</p>
              <p><strong>Phone No:</strong> {profileData.phoneNumber}</p>
            </Card.Text>
          )}
          {!editing && <Button id="editButton" className="button" onClick={handleEdit}>Edit Profile</Button>}
        </Card.Body>
      </Card>
      <ToastContainer />
    </div>
  );
}
 
export default Profile;