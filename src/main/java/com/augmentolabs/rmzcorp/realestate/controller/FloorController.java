package com.augmentolabs.rmzcorp.realestate.controller;


import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class FloorController {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    FloorRepository floorRepository;

    @PostMapping("/building/{id}/floor")
    public ResponseEntity<Floor> addFloor(@PathVariable long id, @RequestBody Floor floor) {
        Optional<Building> building = buildingRepository.findById(id);
        if (!building.isPresent()) {
            throw new IdNotFoundException("Building ID not found: " + id);
        }

        Optional<Floor> newFloor = floorRepository.findById(floor.getFloorNo());
        if (!newFloor.isPresent()) {
            floor.setBuilding(building.get());
            floorRepository.save(floor);
        }


        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(floor.getFloorNo())
                .toUri();

        return ResponseEntity.created(url).build();
    }

//    @GetMapping("/building/{buildingId}/floor/{floorId}")
//    @GetMapping("/building/{buildingId}/floor")
//    @PutMapping("/building/{buildingId}/floor/{floorId}")
//    @DeleteMapping("/building/{buildingId}/floor/{floorId}")
}
