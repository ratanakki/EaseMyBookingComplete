package com.easemybooking.service;

import com.easemybooking.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    Place registerPlace(Place place);

    Optional<Place> getPlaceById(String id);

    List<Place> findByNameContaining(String name);

    List<Place> getAllPlaces();

    Place updatePlace(String id, Place updatedPlace);

    void deletePlaceByName(String placeName);
    
    void deletePlaceByOwnerEmailId(String ownerEmailId);
}
