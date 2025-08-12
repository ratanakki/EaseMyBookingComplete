package com.easemybooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.easemybooking.model.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

    void deleteByPlaceName(String placeName);

    Optional<Place> findByPlaceName(String placeName);

    List<Place> findByPlaceNameContainingIgnoreCase(String placeName);

    void deleteByOwnerEmailId(String ownerEmailId);
}
