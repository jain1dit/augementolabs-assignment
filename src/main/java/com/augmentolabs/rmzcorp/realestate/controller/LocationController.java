package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/city/{cityId}/locations")
    public List<Locations> getAllLocations(@PathVariable long cityId){
        Optional<City> city = cityRepository.findById(cityId);
        if(!city.isPresent()){
            throw new IdNotFoundException("City Id not found: "+ cityId);
        }
        return city.get().getLocations();
    }


    @GetMapping("/city/{cityId}/location/{locationId}")
    public Locations getSpecificLocation(@PathVariable long locationId) throws Exception {
        Optional<Locations> locations = locationRepository.findById(locationId);

        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location Id is not found: " + locationId);
        }

        return locations.get();
    }

    @PostMapping("/city/{cityId}/location")
    public ResponseEntity<Locations> saveNewLocation(@PathVariable long cityId, @RequestBody Locations locations) throws Exception {
        Optional<City> city = cityRepository.findById(cityId);
        if(!city.isPresent()){
            throw new IdNotFoundException("City Id not found"+ cityId);
        }

        Optional<Locations> newLocation = locationRepository.findById(locations.getId());
        if(!newLocation.isPresent()){
            locations.setCity(city.get());
//            validateLocationName(locations.getLocationName());
            locationRepository.save(locations);

        }
        else{
            throw new RuntimeException("Location already present with Location Id: "+ locations.getId());
        }

//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{cityId}")
//                .buildAndExpand(locations.getId())
//                .toUri();

        return ResponseEntity.ok().build();
    }

}
