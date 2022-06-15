package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LocationService {

    List<Locations> getAllLocations(long cityId);

    Locations getSpecificLocationInSpecificCity(long cityId, long locationId);

    Locations saveNewLocation(long cityId, Locations locations);

    void deleteLocation(long locationId);

    Locations updateLocation(long cityId, long locationId, Locations locations);

}
