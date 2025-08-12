package com.easemybooking.service;
 
import com.easemybooking.dto.OwnerDTO;
import com.easemybooking.exception.UserAlreadyExistsException;
import com.easemybooking.exception.UserNotFoundException;
import com.easemybooking.model.Owner;
import com.easemybooking.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;
 
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
 
public class OwnerServiceImplTest {
 
    @Mock
    private OwnerRepository ownerRepository;
 
    @InjectMocks
    private OwnerServiceImpl ownerService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testRegisterNewOwner() throws UserAlreadyExistsException {
        // Arrange
        Owner owner = new Owner();
        owner.setEmailId("test@example.com");
        owner.setPassword("password");
 
        when(ownerRepository.findByEmailId(owner.getEmailId())).thenReturn(Optional.empty());
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
 
        // Act
        Owner registeredOwner = ownerService.register(owner);
 
        // Assert
        verify(ownerRepository, times(1)).findByEmailId(owner.getEmailId());
        verify(ownerRepository, times(1)).save(any(Owner.class));
        assertNotNull(registeredOwner);
        assertEquals("test@example.com", registeredOwner.getEmailId());
        assertTrue(BCrypt.checkpw("password", registeredOwner.getPassword()));
    }
 
    @Test
    public void testRegisterExistingOwner() {
        // Arrange
        Owner owner = new Owner();
        owner.setEmailId("test@example.com");
        owner.setPassword("password");
 
        when(ownerRepository.findByEmailId(owner.getEmailId())).thenReturn(Optional.of(owner));
 
        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> ownerService.register(owner));
        verify(ownerRepository, times(1)).findByEmailId(owner.getEmailId());
        verify(ownerRepository, never()).save(any(Owner.class));
    }
 
    @Test
    public void testLoginSuccessful() throws UserNotFoundException {
        // Arrange
        Owner owner = new Owner();
        owner.setEmailId("test@example.com");
        owner.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
 
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setEmailId("test@example.com");
        ownerDTO.setPassword("password");
 
        when(ownerRepository.findByEmailId(ownerDTO.getEmailId())).thenReturn(Optional.of(owner));
 
        // Act
        Owner loggedInOwner = ownerService.login(ownerDTO);
 
        // Assert
        verify(ownerRepository, times(1)).findByEmailId(ownerDTO.getEmailId());
        assertNotNull(loggedInOwner);
        assertEquals("test@example.com", loggedInOwner.getEmailId());
    }
 
    @Test
    public void testLoginInvalidPassword() {
        // Arrange
        Owner owner = new Owner();
        owner.setEmailId("test@example.com");
        owner.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
 
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setEmailId("test@example.com");
        ownerDTO.setPassword("wrongpassword");
 
        when(ownerRepository.findByEmailId(ownerDTO.getEmailId())).thenReturn(Optional.of(owner));
 
        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> ownerService.login(ownerDTO));
        verify(ownerRepository, times(1)).findByEmailId(ownerDTO.getEmailId());
    }
 
    @Test
    public void testLoginNonExistentUser() {
        // Arrange
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setEmailId("nonexistent@example.com");
        ownerDTO.setPassword("password");
 
        when(ownerRepository.findByEmailId(ownerDTO.getEmailId())).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> ownerService.login(ownerDTO));
        verify(ownerRepository, times(1)).findByEmailId(ownerDTO.getEmailId());
    }
 
    @Test
    public void testRegisterWithNullPassword() {
        // Arrange
        Owner owner = new Owner();
        owner.setEmailId("test@example.com");
        owner.setPassword(null);
 
        when(ownerRepository.findByEmailId(any())).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(NullPointerException.class, () -> ownerService.register(owner));
        verify(ownerRepository, never()).save(any(Owner.class));
    }
 
    @Test
    public void testRegisterEncryptsPassword() throws UserAlreadyExistsException {
        // Arrange
        Owner owner = new Owner();
        owner.setEmailId("test@example.com");
        owner.setPassword("password");
 
        when(ownerRepository.findByEmailId(any())).thenReturn(Optional.empty());
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
 
        // Act
        Owner registeredOwner = ownerService.register(owner);
 
        // Assert
        verify(ownerRepository, times(1)).findByEmailId(any());
        verify(ownerRepository, times(1)).save(any(Owner.class));
        assertNotEquals("password", registeredOwner.getPassword());
        assertTrue(BCrypt.checkpw("password", registeredOwner.getPassword()));
    }
}