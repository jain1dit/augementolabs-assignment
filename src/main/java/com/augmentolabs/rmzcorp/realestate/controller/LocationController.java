package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Location;
import com.augmentolabs.rmzcorp.realestate.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
public class LocationController {


    @Autowired
    LocationService locationService;

    @GetMapping("/city/{cityId}/locations")
    public ResponseEntity<List<Location>> getAllLocations(@PathVariable long cityId){
        return ResponseEntity.ok(locationService.getAllLocations(cityId));
    }

    @GetMapping("/city/{cityId}/location/{locationId}")
    public ResponseEntity<Location> getSpecificLocationInSpecificCity(@PathVariable long cityId, @PathVariable long locationId) throws Exception {
        return ResponseEntity.ok(locationService.getSpecificLocationInSpecificCity(cityId, locationId));
    }

    @PostMapping("/city/{cityId}/location")
    public ResponseEntity<Location> saveNewLocation(@PathVariable long cityId, @RequestBody Location location) throws Exception {
       Location savedLocation = locationService.saveNewLocation(cityId, location);
        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/city/{cityId}/location/" + savedLocation.getId())
                .buildAndExpand(savedLocation.getId())
                .toUri();
        return ResponseEntity.created(url).build();

    }

    @DeleteMapping("/location/{locationId}")
    public ResponseEntity<Location> deleteLocation(@PathVariable long locationId){
        locationService.deleteLocation(locationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/city/{cityId}/location/{locationId}")
    public ResponseEntity<Location> updateLocation(@PathVariable long cityId, @PathVariable long locationId, @RequestBody Location location ){
        return ResponseEntity.ok(locationService.updateLocation(cityId, locationId, location));
    }

}
