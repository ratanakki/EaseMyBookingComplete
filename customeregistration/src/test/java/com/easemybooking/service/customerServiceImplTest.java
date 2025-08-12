package com.easemybooking.service;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
 
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;
 
import com.easemybooking.dto.customerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.customer;
import com.easemybooking.repository.customerRepository;
 
public class customerServiceImplTest {
 
    @Mock
    private customerRepository customerRepository;
 
    @InjectMocks
    private customerServiceImpl customerService;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testGetCustomerByEmailId_UserNotFound() {
        String emailId = "test@example.com";
 
        when(customerRepository.findByEmailId(emailId)).thenReturn(Optional.empty());
 
        assertThrows(UserNotFoundException.class, () -> {
            customerService.getCustomerByEmailId(emailId);}); 
    }
    @Test
  public void testRegister_UserAlreadyExists() {
      customer user = new customer();
      user.setEmailId("test@example.com");
 
      when(customerRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.of(user));
 
      assertThrows(UserAlreadyExistsException.class, () -> {
          customerService.register(user);
      });
 
      verify(customerRepository, never()).save(any(customer.class));
  }
 
    @Test
  public void testRegister_NewUser() throws UserAlreadyExistsException {
      customer user = new customer();
      user.setEmailId("test@example.com");
     user.setPassword("password");
 
      when(customerRepository.findByEmailId(user.getEmailId())).thenReturn(Optional.empty());
      when(customerRepository.save(any(customer.class))).thenReturn(user);
 
      customer newUser = customerService.register(user);
 
     assertNotNull(newUser);
     assertEquals(user.getEmailId(), newUser.getEmailId());
      assertTrue(BCrypt.checkpw("password", newUser.getPassword()));
 
      verify(customerRepository, times(1)).save(any(customer.class));
  }
    @Test
  public void testLogin_UserNotFound() {
      customerDTO userDTO = new customerDTO();
      userDTO.setEmailId("test@example.com");
      userDTO.setPassword("password");
 
      when(customerRepository.findByEmailId(userDTO.getEmailId())).thenReturn(Optional.empty());
 
      assertThrows(UserNotFoundException.class, () -> {
          customerService.login(userDTO);});
  }
  @Test
  public void testLogin_InvalidPassword() {
      customerDTO userDTO = new customerDTO();
      userDTO.setEmailId("test@example.com");
      userDTO.setPassword("wrongpassword");
 
      customer user = new customer();
      user.setEmailId("test@example.com");
      user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
 
      when(customerRepository.findByEmailId(userDTO.getEmailId())).thenReturn(Optional.of(user));
 
      assertThrows(UserNotFoundException.class, () -> {
          customerService.login(userDTO);});
  }
 
    @Test
    public void testLogin_Success() throws UserNotFoundException {
        customerDTO userDTO = new customerDTO();
        userDTO.setEmailId("test@example.com");
        userDTO.setPassword("password");
 
        customer user = new customer();
        user.setEmailId("test@example.com");
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
 
        when(customerRepository.findByEmailId(userDTO.getEmailId())).thenReturn(Optional.of(user));
 
        customer loggedInUser = customerService.login(userDTO);
 
        assertNotNull(loggedInUser);
        assertEquals(user.getEmailId(), loggedInUser.getEmailId());
    }
    @Test
    public void testGetCustomerByEmailId_Success() throws UserNotFoundException {
        String emailId = "test@example.com";
        customer user = new customer();
        user.setEmailId(emailId);
 
        when(customerRepository.findByEmailId(emailId)).thenReturn(Optional.of(user));
 
        customer foundUser = customerService.getCustomerByEmailId(emailId);
 
        assertNotNull(foundUser);
        assertEquals(emailId, foundUser.getEmailId());
    }
    @Test
  public void testUpdateCustomer_UserNotFound() {
      String emailId = "test@example.com";
      customer userDetails = new customer();
 
      when(customerRepository.findByEmailId(emailId)).thenReturn(Optional.empty());
 
      assertThrows(UserNotFoundException.class, () -> {
          customerService.updateCustomer(emailId, userDetails);});
    }
}