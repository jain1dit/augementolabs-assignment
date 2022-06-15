package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.service.LocationService;
import com.augmentolabs.rmzcorp.realestate.service.impl.LocationServiceImpl;
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
    LocationService locationService;

    @GetMapping("/city/{cityId}/locations")
    public ResponseEntity<List<Locations>> getAllLocations(@PathVariable long cityId){
        return ResponseEntity.ok(locationService.getAllLocations(cityId));
    }

    @GetMapping("/city/{cityId}/location/{locationId}")
    public ResponseEntity<Locations> getSpecificLocationInSpecificCity(@PathVariable long cityId, @PathVariable long locationId) throws Exception {
        return ResponseEntity.ok(locationService.getSpecificLocationInSpecificCity(cityId, locationId));
    }

    @PostMapping("/city/{cityId}/location")
    public ResponseEntity<Locations> saveNewLocation(@PathVariable long cityId, @RequestBody Locations locations) throws Exception {
        return ResponseEntity.ok(locationService.saveNewLocation(cityId, locations));
    }
//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{cityId}")
//                .buildAndExpand(locations.getId())
//                .toUri();

    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<Locations> deleteLocation(@PathVariable long locationId){
        locationService.deleteLocation(locationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/city/{cityId}/location/{locationId}")
    public ResponseEntity<Locations> updateLocation(@PathVariable long cityId, @PathVariable long locationId, @RequestBody Locations location ){
        return ResponseEntity.ok(locationService.updateLocation(cityId, locationId, location));
    }

}
