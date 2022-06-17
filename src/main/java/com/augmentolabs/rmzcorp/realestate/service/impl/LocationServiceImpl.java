package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Location;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired CityRepository cityRepository;

  @Autowired LocationRepository locationRepository;

  @Override
  public List<Location> getAllLocations(long cityId) {
    Optional<City> city = cityRepository.findById(cityId);
    if (!city.isPresent()) {
      throw new IdNotFoundException("City Id not found: " + cityId);
    }
    return city.get().getLocations();
  }

  @Override
  public Location getSpecificLocationInSpecificCity(long cityId, long locationId) {
    Optional<City> city = cityRepository.findById(cityId);
    Optional<Location> locations = locationRepository.findById(locationId);

    if (!locations.isPresent() || !city.isPresent()) {
      throw new IdNotFoundException("City Id or Location Id is not found: ");
    }

    List<Location> allLocations = city.get().getLocations();
    for (Location location : allLocations) {
      if (location.getId() == locationId) {
        return location;
      }
    }
    return null;
  }

  @Override
  public Location saveNewLocation(long cityId, Location locations) {
    Optional<City> city = cityRepository.findById(cityId);
    if (!city.isPresent()) {
      throw new IdNotFoundException("City Id not found" + cityId);
    }

    locations.setCity(city.get());
    return locationRepository.save(locations);
  }

  @Override
  public void deleteLocation(long locationId) {
    Optional<Location> locations = locationRepository.findById(locationId);
    if (!locations.isPresent()) {
      throw new IdNotFoundException("Location Id not found: " + locationId);
    }
    locationRepository.deleteById(locationId);
  }

  @Override
  public Location updateLocation(long cityId, long locationId, Location locations) {
    Optional<Location> getLocations = locationRepository.findById(locationId);
    if (getLocations.isPresent()) {
      deleteLocation(locationId);
    }
    return saveNewLocation(cityId, locations);
  }
}
