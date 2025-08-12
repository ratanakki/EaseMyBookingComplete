package com.easemybooking.service;
 
//import com.easemybooking.exception.PlaceAlreadyExistsException;
//import com.easemybooking.exception.PlaceNotFoundException;
//import com.easemybooking.model.Place;
//import com.easemybooking.repository.PlaceRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
// 
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
// 
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
// 
//public class PlaceServiceImplTest {
// 
//    @Mock
//    private PlaceRepository placeRepository;
// 
//    @InjectMocks
//    private PlaceServiceImpl placeService;
// 
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
// 
//   
////    @Test
////    public void testRegisterPlaceAlreadyExists() {
////        // Create a sample place
////        Place existingPlace = new Place();
//// 
////        // Mock behavior of placeRepository.findByPlaceName()
////        when(placeRepository.findByPlaceName(existingPlace.getPlaceName())).thenReturn(Optional.of(existingPlace));
//// 
////        // Call the method under test and assert that it throws PlaceAlreadyExistsException
////        assertThrows(PlaceAlreadyExistsException.class, () -> placeService.registerPlace(existingPlace));
//// 
////        // Verify that save method of repository is never called
////        verify(placeRepository, never()).save(any(Place.class));
////    }
// 
//    @Test
//    public void testGetPlaceByIdNotFound() {
//        // Mock behavior of placeRepository.findById() for non-existent id
//        String nonExistentId = "999";
//        when(placeRepository.findById(nonExistentId)).thenReturn(Optional.empty());
// 
//        // Call the method under test
//        Optional<Place> retrievedPlace = placeService.getPlaceById(nonExistentId);
// 
//        // Assertions
//        assertFalse(retrievedPlace.isPresent());
//    }
// 
//    @Test
//    public void testUpdatePlaceNotFound() {
//        // Non-existent place ID
//        String nonExistentId = "999";
//        Place updatedPlace = new Place();
// 
//        // Mock behavior of placeRepository.findById() for non-existent id
//        when(placeRepository.findById(nonExistentId)).thenReturn(Optional.empty());
// 
//        // Call the method under test and assert that it throws PlaceNotFoundException
//        assertThrows(PlaceNotFoundException.class, () -> placeService.updatePlace(nonExistentId, updatedPlace));
// 
//        // Verify interactions
//        verify(placeRepository, times(1)).findById(eq(nonExistentId));
//        verify(placeRepository, never()).save(any(Place.class));
//    }
// 
//    @Test
//    public void testDeletePlaceByName() {
//        // Sample data
//        String placeName = "Place A";
// 
//        // Call the method under test
//        placeService.deletePlaceByName(placeName);
// 
//        // Verify interactions
//        verify(placeRepository, times(1)).deleteByPlaceName(eq(placeName));
//    }
// 
//    @Test
//    public void testDeletePlaceByOwnerEmailId() {
//        // Sample data
//        String ownerEmailId = "owner@example.com";
// 
//        // Call the method under test
//        placeService.deletePlaceByOwnerEmailId(ownerEmailId);
// 
//        // Verify interactions
//        verify(placeRepository, times(1)).deleteByOwnerEmailId(eq(ownerEmailId));
//    }
//}
