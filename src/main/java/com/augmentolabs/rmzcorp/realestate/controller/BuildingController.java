package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class BuildingController {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/locations/{locationId}/buildings")
    public List<Building> getAllBuildings(@PathVariable long locationId) {
        Optional<Locations> locations = locationRepository.findById(locationId);
        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location ID not found: " + locationId);
        }

        return locations.get().getBuildings();
    }

    @GetMapping("/building/{buildingId}")
    public Building getSpecificBuilding(@PathVariable long buildingId) throws Exception {
        Optional<Building> buildings = buildingRepository.findById(buildingId);

        if (!buildings.isPresent()) {
            throw new IdNotFoundException("Facility Id is not found: " + buildingId);
        }

        return buildings.get();
    }

    @PostMapping("/location/{locationId}}/building")
    public ResponseEntity<Building> saveNewBuilding(@PathVariable long locationId, @RequestBody Building building) {
        Optional<Locations> locations = locationRepository.findById(locationId);
        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location Id not found" + locationId);
        }

        Optional<Building> newBuilding = buildingRepository.findById(building.getId());
        if (!newBuilding.isPresent()) {
            building.setLocations(locations.get());
            Building buildingGenerated = buildingRepository.save(building);
            return ResponseEntity.ok(buildingGenerated);
        } else {
            throw new RuntimeException("Building already exists with Building ID: " + building.getId());
        }


//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{locationId}")
//                .buildAndExpand(building.getId())
//                .toUri();


    }
}
