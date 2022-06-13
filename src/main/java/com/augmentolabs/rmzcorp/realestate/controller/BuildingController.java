package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController

public class BuildingController {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/building/{id}")
    public Building retrieveMeterByBuildingId(@PathVariable long id) throws Exception {
        Optional<Building> buildings = buildingRepository.findById(id);

        if (!buildings.isPresent()) {
            throw new IdNotFoundException("Facility Id is not found: " + id);
        }

        return buildings.get();
    }

    @PostMapping("/location/{locationId}}/building/")
    public ResponseEntity<Building> createBuilding(@PathVariable long id, @RequestBody Building building){
        Optional<Locations> locations = locationRepository.findById(id);
        if(!locations.isPresent()){
            throw new IdNotFoundException("Location Id not found"+ id);
        }

        Optional<Building> newBuilding = buildingRepository.findById(building.getId());
        if(!newBuilding.isPresent()){
            building.setLocations(locations.get());
            buildingRepository.save(building);
        }


        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(building.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }
}
