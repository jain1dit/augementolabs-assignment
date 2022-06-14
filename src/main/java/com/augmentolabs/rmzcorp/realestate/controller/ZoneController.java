package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;



@RestController
public class ZoneController {

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    BuildingRepository buildingRepository;


    @GetMapping("/zone/{zoneId}")
    public Zone getSpecificZone(@PathVariable long zoneId) throws Exception {
        Optional<Zone> zone = zoneRepository.findById(zoneId);


        if (!zone.isPresent()) {
            throw new IdNotFoundException("Facility Id not found: " + zoneId);
        }

        return zone.get();
    }


    @PostMapping("/building/{buildingId}/floor/{floorNo}/zone")
    public ResponseEntity<Object> saveNewZone(@PathVariable long buildingId, @PathVariable int floorNo, @RequestBody Zone zone) throws Exception {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if(!building.isPresent()){
            throw new IdNotFoundException("Building Id not found"+ buildingId);
        }
        // search floor in building
        // search zone on that floor
        // if not present then create
        // else zone is aleready present
        Optional<Zone> newZone = zoneRepository.findById(zone.getId());
        if(!newZone.isPresent()){
            zone.setFloor(building.get().getFloors().get(floorNo));
            zoneRepository.save(zone);
        }

//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{buildingId}")
//                .buildAndExpand(zone.getId())
//                .toUri();

        return ResponseEntity.ok().build();
    }
}
