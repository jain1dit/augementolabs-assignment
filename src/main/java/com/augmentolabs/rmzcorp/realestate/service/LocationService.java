package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Location;

import java.util.List;

public interface LocationService {

    List<Location> getAllLocations(long cityId);

    Location getSpecificLocationInSpecificCity(long cityId, long locationId);

    Location saveNewLocation(long cityId, Location locations);

    void deleteLocation(long locationId);

    Location updateLocation(long cityId, long locationId, Location locations);

}
