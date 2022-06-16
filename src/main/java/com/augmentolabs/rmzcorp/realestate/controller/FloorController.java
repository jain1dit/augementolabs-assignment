package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorId;
import com.augmentolabs.rmzcorp.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class FloorController {

    @Autowired
    FloorService floorService;

    @GetMapping("/building/{buildingId}/floor")
    public ResponseEntity<List<Floor>> getFloors(@PathVariable long buildingId) {
        return ResponseEntity.ok(floorService.getFloors(buildingId));
    }

    @PostMapping("/building/{buildingId}/floor")
    public ResponseEntity<Floor> addFloor(@PathVariable long buildingId, @RequestBody Floor floor) {
       Floor savedFloor = floorService.addFloor(buildingId, floor);
        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/building/{buildingId}/floor/"+ savedFloor.getFloorNo())
                .buildAndExpand(savedFloor.getFloorNo())
                .toUri();
        return ResponseEntity.created(url).build();

    }

    @DeleteMapping("/floor/{floorId}")
    public ResponseEntity<Floor> deleteFloor(@PathVariable FloorId floorId){
        floorService.deleteFloor(floorId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/building/{buildingId}/floor/{floorId}")
    public ResponseEntity<Floor> updateFloor(@PathVariable long buildingId, @PathVariable FloorId floorId, @RequestBody Floor floor){
        return ResponseEntity.ok(floorService.updateFloor(buildingId, floorId, floor));
    }

}
