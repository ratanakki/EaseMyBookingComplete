import { navigate } from '@reach/router';
import { Link ,useNavigate } from 'react-router-dom';
import { FiHome, FiMapPin, FiLogOut } from 'react-icons/fi';
import { toast } from 'react-toastify';
import './Sidebar.css';

function Sidebar(){
const navigate=useNavigate();
  const handleLogout = () => {
    // Handle logout logic here
    console.log('User logged out');
    //toast.announce('User logged out');
    localStorage.clear();
    navigate('/');
    
  };
 
  return (
    <div className="sidebar-container">
      <div className="toolbar-placeholder"></div>
      <ul className="sidebar-list">
        <li>
          <Link to="/booking">
          <FiHome />
            <span>Booking Details</span>
          </Link>
        </li>
        <li>
          <Link to="/register">
          <FiMapPin />
            <span>Register Place</span>
          </Link>
        </li>
      </ul>
      <div className="flex-grow"></div>
      <div className="logout-button">
        <button onClick={handleLogout}>
        <FiLogOut />
        Logout</button>
      </div>
    </div>
  );
};
 
export default Sidebar;
