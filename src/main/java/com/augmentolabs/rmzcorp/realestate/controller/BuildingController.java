package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class BuildingController {

  @Autowired BuildingService buildingService;

  @GetMapping("/locations/{locationId}/buildings")
  public ResponseEntity<List<Building>> getAllBuildings(@PathVariable long locationId) {
    return ResponseEntity.ok(buildingService.getAllBuildings(locationId));
  }

  @GetMapping("/building/{buildingId}")
  public ResponseEntity<Building> getSpecificBuilding(@PathVariable long buildingId)
      throws Exception {
    return ResponseEntity.ok(buildingService.getSpecificBuilding(buildingId));
  }

  @PostMapping("/location/{locationId}/building")
  public ResponseEntity<Building> saveNewBuilding(
      @PathVariable long locationId, @RequestBody Building building) {
    Building savedBuilding = buildingService.saveNewBuilding(locationId, building);

    URI url =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/location/{locationId}/building/" + savedBuilding.getId())
            .buildAndExpand(savedBuilding.getId())
            .toUri();

    return ResponseEntity.created(url).build();
  }

  @DeleteMapping("/building/{buildingId}")
  public ResponseEntity<Building> deleteBuilding(@PathVariable long buildingId) {
    buildingService.deleteBuilding(buildingId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/location/{locationId}/building/{buildingId}")
  public ResponseEntity<Building> updateBuilding(
      @PathVariable long locationId,
      @PathVariable long buildingId,
      @RequestBody Building building) {
    return ResponseEntity.ok(buildingService.updateBuilding(locationId, buildingId, building));
  }
}
