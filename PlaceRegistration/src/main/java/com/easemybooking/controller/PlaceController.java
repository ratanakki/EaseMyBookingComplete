package com.easemybooking.controller;

import com.easemybooking.exception.PlaceAlreadyExistsException;
import com.easemybooking.exception.PlaceNotFoundException;
import com.easemybooking.model.Place;
import com.easemybooking.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
@RequestMapping("/api/v1")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/users/places")
    public ResponseEntity<?> registerPlace(
    		
            @RequestPart("ownerEmailId") String ownerEmailId,
            @RequestPart("placeName") String placeName,
            @RequestPart("img") MultipartFile imgFile,
            @RequestPart("description") String description,
            @RequestPart("location") String location,
            @RequestPart("map") String map) {
        try {
            byte[] imgBytes = imgFile.getBytes();
            Place place = new Place(ownerEmailId, placeName, imgBytes, description, location, map);
            Place registeredPlace = placeService.registerPlace(place);
            return new ResponseEntity<>(registeredPlace, HttpStatus.CREATED);
        } catch (PlaceAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing image file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/places")
    public ResponseEntity<List<Place>> getAllPlaces(@RequestParam(value = "name", required = false) String name) {
        List<Place> places;
        if (name != null && !name.isEmpty()) {
            places = placeService.findByNameContaining(name);
        } else {
            places = placeService.getAllPlaces();
        }
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @PutMapping("/users/places/{id}")
    public ResponseEntity<?> updatePlace(@PathVariable String id, @RequestBody Place updatedPlace) {
        try {
            Place updated = placeService.updatePlace(id, updatedPlace);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (PlaceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/places/{placeName}")
    public ResponseEntity<?> deletePlaceByName(@PathVariable String placeName) {
        try {
            placeService.deletePlaceByName(placeName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PlaceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/places/owner/{ownerEmailId}")
    public ResponseEntity<?> deletePlaceByOwnerEmailId(@PathVariable String ownerEmailId) {
        try {
            placeService.deletePlaceByOwnerEmailId(ownerEmailId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PlaceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
