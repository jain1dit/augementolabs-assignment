package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class FloorController {

  @Autowired FloorService floorService;

  @GetMapping("/building/{buildingId}/floor")
  public ResponseEntity<List<Floor>> getFloors(@PathVariable long buildingId) {
    return ResponseEntity.ok(floorService.getFloors(buildingId));
  }

  @PostMapping("/building/{buildingId}/floor")
  public ResponseEntity<Floor> addFloor(@PathVariable long buildingId) throws Exception {
    Floor savedFloor = floorService.addFloor(buildingId);
    URI url =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/building/{buildingId}/floor/" + savedFloor.getId())
            .buildAndExpand(savedFloor.getId())
            .toUri();
    return ResponseEntity.created(url).build();
  }

  @DeleteMapping("/building/{buildingId}/floor/{floorId}")
  public ResponseEntity<Floor> deleteFloor(
      @PathVariable long buildingId, @PathVariable long floorId) {
    floorService.deleteFloor(buildingId, floorId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/building/{buildingId}/floor/{floorId}")
  public ResponseEntity<Floor> updateFloor(
      @PathVariable long buildingId, @PathVariable long floorId, @RequestBody Floor floor)
      throws Exception {
    return ResponseEntity.ok(floorService.updateFloor(buildingId, floorId, floor));
  }
}
