package com.easemybooking.service;

import com.easemybooking.exception.PlaceAlreadyExistsException;
import com.easemybooking.exception.PlaceNotFoundException;
import com.easemybooking.model.Place;
import com.easemybooking.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place registerPlace(Place place) throws PlaceAlreadyExistsException {
        // Check if the place already exists in the database by name
        Optional<Place> existingPlace = placeRepository.findByPlaceName(place.getPlaceName());

        if (existingPlace.isPresent()) {
            throw new PlaceAlreadyExistsException("Place with this name already exists.");
        } else {
            return placeRepository.save(place);
        }
    }

    @Override
    public Optional<Place> getPlaceById(String id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> findByNameContaining(String name) {
        return placeRepository.findByPlaceNameContainingIgnoreCase(name);
    }

    @Override
    public Place updatePlace(String id, Place updatedPlace) {
        Optional<Place> existingPlaceOptional = placeRepository.findById(id);
        if (existingPlaceOptional.isPresent()) {
            Place existingPlace = existingPlaceOptional.get();
            existingPlace.setPlaceName(updatedPlace.getPlaceName());
            existingPlace.setImg(updatedPlace.getImg());
            existingPlace.setDescription(updatedPlace.getDescription());
            existingPlace.setLocation(updatedPlace.getLocation());
            existingPlace.setMap(updatedPlace.getMap());
            return placeRepository.save(existingPlace);
        } else {
            throw new PlaceNotFoundException("Place with ID " + id + " not found");
        }
    }

    @Override
    public void deletePlaceByName(String placeName) {
        placeRepository.deleteByPlaceName(placeName);
    }
    
    @Override
    public void deletePlaceByOwnerEmailId(String ownerEmailId) {
        placeRepository.deleteByOwnerEmailId(ownerEmailId);
    }
}
