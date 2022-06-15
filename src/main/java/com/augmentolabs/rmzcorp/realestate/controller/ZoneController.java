package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.service.ZoneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ZoneController {


    @Autowired
    ZoneServices zoneServices;


    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<Zone> getSpecificZone(@PathVariable long zoneId) throws Exception {
        return ResponseEntity.ok(zoneServices.getSpecificZone(zoneId));
    }

    @PostMapping("/building/{buildingId}/floor/{floorNo}/zone")
    public ResponseEntity<Object> saveNewZone(@PathVariable long buildingId, @PathVariable long floorNo, @RequestBody Zone zone) throws Exception {
        return ResponseEntity.ok(zoneServices.saveNewZone(buildingId, floorNo, zone));
//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{buildingId}")
//                .buildAndExpand(zone.getId())
//                .toUri();

    }

    @DeleteMapping("/zone/{zoneId}")
    public ResponseEntity<Zone> deleteZone(@PathVariable long zoneId){
        zoneServices.deleteZone(zoneId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/building/{buildingId}/floor/{floorNo}/zone/{zoneId}")
    public ResponseEntity<Zone> updateZone(@PathVariable long buildingId, @PathVariable long floorNo, @PathVariable long zoneId, @RequestBody Zone zone ){
        return ResponseEntity.ok(zoneServices.updateZoneId(buildingId, floorNo, zoneId, zone));
    }
}
