package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
import com.augmentolabs.rmzcorp.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return ResponseEntity.ok(floorService.addFloor(buildingId, floor));
//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{buildingId}")
//                .buildAndExpand(floor.getFloorNo())
//                .toUri();

    }

    @DeleteMapping("/building/{buildingId}/floor/{floorNo}")
    public ResponseEntity<Floor> deleteFloor(@PathVariable long buildingId, @PathVariable long floorNo){
        floorService.deleteFloor(buildingId, floorNo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/building/{buildingId}/floor/{floorNo}")
    public ResponseEntity<Floor> updateFloor(@PathVariable long buildingId, @PathVariable long floorNo, @RequestBody Floor floor){
        return ResponseEntity.ok(floorService.updateFloor(buildingId, floorNo, floor));
    }

}
