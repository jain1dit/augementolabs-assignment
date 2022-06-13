package com.augmentolabs.rmzcorp.realestate.controllerTest;

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
import java.util.Optional;

@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/location/{id}")
    public Locations retrieveMeterByLocationId(@PathVariable long id) throws Exception {
        Optional<Locations> locations = locationRepository.findById(id);

        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location Id is not found: " + id);
        }

        return locations.get();
    }

    @PostMapping("/city/{id}/location")
    public ResponseEntity<Locations> createLocation(@PathVariable long id, @RequestBody Locations locations) throws Exception {
        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()){
            throw new IdNotFoundException("City Id not found"+ id);
        }

        Optional<Locations> newLocation = locationRepository.findById(locations.getId());
        if(!newLocation.isPresent()){
            locations.setCity(city.get());
//            validateLocationName(locations.getLocationName());
            locationRepository.save(locations);

        }

        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(locations.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }

}
