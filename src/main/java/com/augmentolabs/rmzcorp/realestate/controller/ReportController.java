package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.exceptions.LocationNameException;
import com.augmentolabs.rmzcorp.realestate.exceptions.ZoneNameException;
import com.augmentolabs.rmzcorp.realestate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
public class ReportController {
    @Autowired
    CitiesRepository cityRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    ZoneRepository zoneRepository;


    //Get Entities Information:

    @GetMapping("/zone/{id}")
    public Zone retrieveMeterByZoneId(@PathVariable long id) throws Exception {
        Optional<Zone> zone = zoneRepository.findById(id);

        if (!zone.isPresent()) {
            throw new IdNotFoundException("Facility Id is not found: " + id);
        }

        return zone.get();
    }


    @GetMapping("/building/{id}")
    public Building retrieveMeterByBuildingId(@PathVariable long id) throws Exception {
        Optional<Building> buildings = buildingRepository.findById(id);

        if (!buildings.isPresent()) {
            throw new IdNotFoundException("Facility Id is not found: " + id);
        }

        return buildings.get();
    }


    @GetMapping("/location/{id}")
    public Locations retrieveMeterByLocationId(@PathVariable long id) throws Exception {
        Optional<Locations> locations = locationRepository.findById(id);

        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location Id is not found: " + id);
        }

        return locations.get();
    }



    //Create Entities:


    @PostMapping("/city")
    public ResponseEntity<City> createCity(@Valid @RequestBody City city) throws Exception {
         Optional<City> newCity = cityRepository.findById(city.getId());
         if(!newCity.isPresent()){
             cityRepository.save(city);
         }
         else {
             throw new Exception("City already Present with Id: "+ city.getId());
         }

        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();

        return ResponseEntity.created(url).build();


    }

    @PostMapping("/location/in/cityId/{id}")
    public ResponseEntity<Locations> createLocation(@PathVariable long id, @RequestBody Locations locations) throws Exception {
        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()){
            throw new IdNotFoundException("City Id not found"+ id);
        }

        Optional<Locations> newLocation = locationRepository.findById(locations.getId());
        if(!newLocation.isPresent()){
            locations.setCity(city.get());
            validateLocationName(locations.getLocationName());
            locationRepository.save(locations);

        }

        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(locations.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }

    @PostMapping("/building/in/locationId/{id}")
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

    @PostMapping("add/floor/in/buildingId/{id}")
    public ResponseEntity<Floor> addFloor(@PathVariable long id, @RequestBody Floor floor){
        Optional<Building> building = buildingRepository.findById(id);
        if(!building.isPresent()){
            throw new IdNotFoundException("Building ID not found: "+ id);
        }

        Optional<Floor> newFloor = floorRepository.findById(floor.getFloorNo());
        if(!newFloor.isPresent()){
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

    @PostMapping("/zone/in/buildingId/{id}/at/floorNo/{floorNo}")
    public ResponseEntity<Object> createZone(@PathVariable long id, @PathVariable int floorNo, @RequestBody Zone zone/*, @RequestBody Meter meter*/) throws Exception {
        Optional<Building> building = buildingRepository.findById(id);
        if(!building.isPresent()){
            throw new IdNotFoundException("Building Id not found"+ id);
        }

        Optional<Zone> newZone = zoneRepository.findById(zone.getId());
        if(!newZone.isPresent()){
            List<Floor> floors = building.get().getFloors();
            zone.setFloor(floors.get(floorNo));

            validateZoneName(zone.getZoneName());
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

    @PostMapping("create/meter/with/zoneId/{id}")
    public ResponseEntity<Meter> createMeterByZoneId(@PathVariable long id, @RequestBody Meter meter){
        Optional<Zone> zone = zoneRepository.findById(id);
        if(!zone.isPresent()){
            throw new IdNotFoundException("Id not found: " + id);

        }

        Optional<Meter> newMeter = meterRepository.findById(meter.getId());
        if(!newMeter.isPresent()){
            meter.setZone(zone.get());
            meterRepository.save(meter);
        }

        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(meter.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }


    // Apply own validations:

    public static void validateZoneName(String zoneName) throws Exception{
        if(zoneName.length()>5){
            throw new ZoneNameException("Write any one: North, South, East, West");
        }
    }

    public static void validateLocationName(String locationName) throws Exception{
        String[] characters = locationName.split("");
        for (String character : characters) {
            if (character.contains("0") || character.contains("1") || character.contains("2") || character.contains("3") || character.contains("4") || character.contains("5") || character.contains("6") || character.contains("7") || character.contains("8") || character.contains("9")) {
                throw new LocationNameException("Write any one: North, South, East, West");
            }
        }
    }

}
