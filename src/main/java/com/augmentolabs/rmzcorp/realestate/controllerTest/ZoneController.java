package com.augmentolabs.rmzcorp.realestate.controllerTest;

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


    @GetMapping("/zone/{id}")
    public Zone retrieveMeterByZoneId(@PathVariable long id) throws Exception {
        Optional<Zone> zone = zoneRepository.findById(id);


        if (!zone.isPresent()) {
            throw new IdNotFoundException("Facility Id is not found: " + id);
        }

        return zone.get();
    }

    @PostMapping("/buildingId/{buildingId}/at/floorNo/{floorNo}/zone")
    public ResponseEntity<Object> createZone(@PathVariable long id, @PathVariable int floorNo, @RequestBody Zone zone/*, @RequestBody Meter meter*/) throws Exception {
        Optional<Building> building = buildingRepository.findById(id);
        if(!building.isPresent()){
            throw new IdNotFoundException("Building Id not found"+ id);
        }

        Optional<Zone> newZone = zoneRepository.findById(zone.getId());
        if(!newZone.isPresent()){
            List<Floor> floors = building.get().getFloors();
            zone.setFloor(floors.get(floorNo));


            /*Zone newZone =*/ zoneRepository.save(zone);
        }


        //Meter newMeter = meterRepository.save(meter);
        //newZone.setMeterType(Collections.singletonList(newMeter));


        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(zone.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }
}
