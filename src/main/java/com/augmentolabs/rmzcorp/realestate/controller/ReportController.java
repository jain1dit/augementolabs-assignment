package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {
    @Autowired
    CitiesRepository cityRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    LocationRepository facilityRepository;

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    ZoneRepository zoneRepository;


    @GetMapping("/meter/by/zone/{id}")
    public Zone retrieveMeterByZoneId(@PathVariable long id) throws Exception {
        Optional<Zone> zone = zoneRepository.findById(id);

        if (!zone.isPresent()) {
            throw new Exception("Facility Id is not found: " + id);
        }

        return zone.get();
    }

    @GetMapping("/meter/by/building/{id}")
    public Building retrieveZoneByFacilityId(@PathVariable long id) throws Exception {
        Optional<Building> buildings = buildingRepository.findById(id);

        if (!buildings.isPresent()) {
            throw new Exception("Facility Id is not found: " + id);
        }

        return buildings.get();
    }

}
