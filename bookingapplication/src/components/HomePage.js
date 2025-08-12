import React, { useState } from 'react';
import { CSSTransition, TransitionGroup } from 'react-transition-group';
import Modal from '../pages/Modal';
import axios from 'axios';
import { useNavigate} from 'react-router-dom';
import './HomePage.css';
import { toast } from 'react-toastify';


function Places() {
  const topPlaces = [
   
   
    {
      name: 'Colva Beach',
      description: 'Colva Beach, located in South Goa, stretches for approximately 2.4 kilometers along the Arabian Sea. This pristine beach boasts powdery white sands framed by swaying coconut trees, creating a picturesque setting. As you walk along the shore, you’ll find vibrant shacks, lively nightclubs, and charming souvenir stalls.',
      image: 'assets/Colva.jpg',
    },
    {
      name: 'Red Fort',
      description: 'The Red Fort or Lal Qila is an iconic monument synonymous with the rich political heritage, freedom, and sovereignty of India. Built in the 17 th century CE, by the Mughal Emperor Shah Jahan, this fort complex is one of the largest and grandest in India',
      image: 'assets/redfort.jpg',
    },
    {
      name: 'Hampi',
      description: 'Hampi, also known as the Group of Monuments at Hampi,It was the ancient capital of the Vijayanagara Empire, flourishing from the 14th to the 16th century. Hampi boasts over 1,600 monuments, including majestic temples, palaces, forts, and water structures, showcasing the grandeur of a bygone era',
      image: 'assets/hampi.jpg',
    },

    // Add more attractions with their respective images and descriptions
  ];

  return (
    <div>
      <h2>Top Places</h2>
      <TransitionGroup className="places-container">
        {topPlaces.map((place) => (
          <CSSTransition key={place.name} timeout={500} classNames="place" data-aos="fade-up">
            <div className="place">
              <img src={place.image} alt={place.name} className="place-image" />
              <h3>{place.name}</h3>
              <p>{place.description}</p>
            </div>
          </CSSTransition>
        ))}
      </TransitionGroup>
    </div>
  );
}

function CustomerLoginForm({ onSubmit, setEmail, setPassword, email, password, errors, handleRegisterLink }) {
  return (
    <div>
      <h2>Log in as Customer</h2>
      <form className="login-form" onSubmit={onSubmit}>
        <label htmlFor="login-email">Email:</label>
        <input
          type="email"
          id="login-email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        {errors.email && <p className="error">{errors.email}</p>}
        <label htmlFor="login-password">Password:</label>
        <input
          type="password"
          id="login-password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        {errors.password && <p className="error">{errors.password}</p>}
        <button type="submit">Login</button>
      </form>
      <p className="register-text">If not already Registered? <span className="register-link" onClick={() => handleRegisterLink(true)}>Register</span></p>
    </div>
  );
}

function OwnerLoginForm({ onSubmit, setEmail, setPassword, email, password, errors, handleRegisterLink }) {
  return (
    <div>
      <h2>Log in as Owner</h2>
      <form className="login-form" onSubmit={onSubmit}>
        <label htmlFor="login-email">Email:</label>
        <input
          type="email"
          id="login-email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        {errors.email && <p className="error">{errors.email}</p>}
        <label htmlFor="login-password">Password:</label>
        <input
          type="password"
          id="login-password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        {errors.password && <p className="error">{errors.password}</p>}
        <button type="submit">Login</button>
      </form>
      <p className="register-text">If not already Registered? <span className="register-link" onClick={() => handleRegisterLink(false)}>Register</span></p>
    </div>
  );
}

const HomePage = ()=> {
  const navigate = useNavigate();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isLoginFormOpen, setIsLoginFormOpen] = useState(false);
  const [isRegisterFormOpen, setIsRegisterFormOpen] = useState(false);
  const [isCustomerRegister, setIsCustomerRegister] = useState(false);
  const [isOwnerLogin, setIsOwnerLogin] = useState(false);

  const [loginEmail, setLoginEmail] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [loginErrors, setLoginErrors] = useState({});
  const [isContactUsModalOpen, setIsContactUsModalOpen] = useState(false);

  const [registerEmail, setRegisterEmail] = useState('');
  const [registerFirstName, setRegisterFirstName] = useState('');
  const [registerLastName, setRegisterLastName] = useState('');
  const [registerPhone, setRegisterPhone] = useState('');
  const [registerPassword, setRegisterPassword] = useState('');
  const [registerConfirmPassword, setRegisterConfirmPassword] = useState('');
  const [registerErrors, setRegisterErrors] = useState({});

  const handleModalToggle = () => {
    console.log('Toggling modal');
    setIsModalOpen(!isModalOpen);
    setIsLoginFormOpen(false);
    setIsRegisterFormOpen(false);
  };

  const handleContactUsModalToggle = () => {
    setIsContactUsModalOpen(!isContactUsModalOpen);
  };

  const handleLoginOption = (isOwner) => {
    setIsOwnerLogin(isOwner);
    setIsLoginFormOpen(true);
    setIsRegisterFormOpen(false);
  };

  const handleRegisterLink = (isCustomer) => {
    setIsCustomerRegister(isCustomer);
    setIsRegisterFormOpen(true);
    setIsLoginFormOpen(false);
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

  const validateRegisterForm = () => {
    const errors = {};

    if (!registerEmail) {
      errors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(registerEmail)) {
      errors.email = 'Email address is invalid';
    }

    if (!registerFirstName) {
      errors.firstName = 'First name is required';
    }

    if (!registerLastName) {
      errors.lastName = 'Last name is required';
    }

    if (!registerPhone) {
      errors.phone = 'Phone number is required';
    } else if (!/^\d{10,12}$/.test(registerPhone)) {
      errors.phone = 'Phone number must be 10 to 12 digits';
    }

    if (!registerPassword) {
      errors.password = 'Password is required';
    } else if (!/(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}/.test(registerPassword)) {
      errors.password = 'Password must be at least 8 characters and contain one capital letter and one special character';
    }

    if (registerPassword !== registerConfirmPassword) {
      errors.confirmPassword = 'Passwords do not match';
    }

    setRegisterErrors(errors);
    return Object.keys(errors).length === 0;
  };

  const handleOwnerLoginSubmit = (e) => {
    e.preventDefault();
    if (validateLoginForm()) {
      const formData = {
        emailId: loginEmail,
        password: loginPassword
      };
      axios.post('http://localhost:9005/api/v1/users/ownerlogin', formData)
        .then((response) => {
          console.log(response.data);
          alert("Logged in succesfully");
          localStorage.setItem("Owneremail",formData.emailId);
          localStorage.setItem("token",response.data.token);
          // Navigate to owner dashboard
          navigate("/ownerdashboard");
        })
        .catch((error) => {
          if(error.response.status===404){
            alert("customer email/password is incorrect");
          }
          console.error(error);
        });
    }
  };

  const handleCustomerLoginSubmit = (e) => {
    e.preventDefault();
    if (validateLoginForm()) {
      const formData = {
        emailId: loginEmail,
        password: loginPassword
      };
      axios.post('http://localhost:9006/api/v1/users/customerlogin', formData)
        .then((response) => {
          console.log(response.data);
          toast.success("Logged in succesfully");
          localStorage.setItem("email",formData.emailId);
          localStorage.setItem("token",response.data.token);
          // Navigate to customer dashboard
          navigate('/home');
        })
        .catch((error) => {
          if(error.response.status===404){
            alert("customer email/password is incorrect");
          }
         
          console.error(error);
        });
    }
  };

  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    if (validateRegisterForm()) {
      const formData = {
        emailId: registerEmail,
        firstName: registerFirstName,
        lastName: registerLastName,
        phNo: registerPhone,
        password: registerPassword,
        conPass: registerConfirmPassword
      };

      const endpoint = isCustomerRegister
        ? 'http://localhost:9006/api/v1/users/customerRegister'
        : 'http://localhost:9005/api/v1/users/ownerregister';

      axios.post(endpoint, formData)
        .then((response) => {
          console.log(response.data);
          alert("Registration successful! Please login.");
          setIsRegisterFormOpen(false);
          setIsLoginFormOpen(true);
          if (isCustomerRegister) {
            setIsOwnerLogin(false);
          } else {
            setIsOwnerLogin(true);
          }
        })
        .catch((error) => {
          if(error.response.status===409){
            alert("User already exists");
          }
          console.error(error);
        });
    }
  };

  return (
    <div className="home-page">
      <header className="homepage-header">
        <h1>EaseMyBooking</h1>
        <button onClick={handleModalToggle}>Login</button>
      </header>
      <nav>
        <ul className="navbar">
        <li onClick={() => navigate("/places")}> Places</li>
     
    
          <li onClick={handleContactUsModalToggle}>Contact Us</li>
        </ul>
      </nav>
      <Places />

      <Modal isOpen={isModalOpen} onClose={handleModalToggle}>
        {!isLoginFormOpen && !isRegisterFormOpen && (
          <div className="login-options">
            <h2>Choose Login Type</h2>
            <button onClick={() => handleLoginOption(true)}>Login as Owner</button>
            <button onClick={() => handleLoginOption(false)}>Login as Customer</button>
          </div>
        )}
        {isLoginFormOpen && isOwnerLogin && (
          <OwnerLoginForm
            onSubmit={handleOwnerLoginSubmit}
            setEmail={setLoginEmail}
            setPassword={setLoginPassword}
            email={loginEmail}
            password={loginPassword}
            errors={loginErrors}
            handleRegisterLink={handleRegisterLink}
          />
        )}
        {isLoginFormOpen && !isOwnerLogin && (
          <CustomerLoginForm
            onSubmit={handleCustomerLoginSubmit}
            setEmail={setLoginEmail}
            setPassword={setLoginPassword}
            email={loginEmail}
            password={loginPassword}
            errors={loginErrors}
            handleRegisterLink={handleRegisterLink}
          />
        )}
        {isRegisterFormOpen && (
          <div>
            <h2>Register {isCustomerRegister ? 'Customer' : 'Owner'}</h2>
            <form className="register-form" onSubmit={handleRegisterSubmit}>
              <label htmlFor="register-email">Email:</label>
              <input
                type="email"
                id="register-email"
                value={registerEmail}
                onChange={(e) => setRegisterEmail(e.target.value)}
              />
              {registerErrors.email && <p className="error">{registerErrors.email}</p>}
              <label htmlFor="register-firstname">First Name:</label>
              <input
                type="text"
                id="register-firstname"
                value={registerFirstName}
                onChange={(e) => setRegisterFirstName(e.target.value)}
              />
              {registerErrors.firstName && <p className="error">{registerErrors.firstName}</p>}
              <label htmlFor="register-lastname">Last Name:</label>
              <input
                type="text"
                id="register-lastname"
                value={registerLastName}
                onChange={(e) => setRegisterLastName(e.target.value)}
              />
              {registerErrors.lastName && <p className="error">{registerErrors.lastName}</p>}
              <label htmlFor="register-phone">Phone Number:</label>
              <input
                type="tel"
                id="register-phone"
                value={registerPhone}
                onChange={(e) => setRegisterPhone(e.target.value)}
              />
              {registerErrors.phone && <p className="error">{registerErrors.phone}</p>}
              <label htmlFor="register-password">Password:</label>
              <input
                type="password"
                id="register-password"
                value={registerPassword}
                onChange={(e) => setRegisterPassword(e.target.value)}
              />
              {registerErrors.password && <p className="error">{registerErrors.password}</p>}
              <label htmlFor="register-confirm-password">Confirm Password:</label>
              <input
                type="password"
                id="register-confirm-password"
                value={registerConfirmPassword}
                onChange={(e) => setRegisterConfirmPassword(e.target.value)}
              />
              {registerErrors.confirmPassword && <p className="error">{registerErrors.confirmPassword}</p>}
              <button type="submit">Register</button>
            </form>
          </div>
        )}
      </Modal>

      <Modal isOpen={isContactUsModalOpen} onClose={handleContactUsModalToggle}>
        <div className="contact-us">
          <h2>Contact Us</h2>
          <form>
            <label htmlFor="contact-name">Name:</label>
            <input type="text" id="contact-name" />
            <label htmlFor="contact-email">Email:</label>
            <input type="email" id="contact-email" />
            <label htmlFor="contact-message">Message:</label>
            <textarea id="contact-message"></textarea>
            <button type="submit">Send</button>
          </form>
        </div>
      </Modal>
    </div>
  );
}

export default HomePage;
