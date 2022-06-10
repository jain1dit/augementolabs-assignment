package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestControllerService {
    @Autowired
    CitiesRepository citiesRepository;

    @Autowired
    BuildingRepository buildingServices;

    @Autowired
    FacilityRepository facilityRepository;

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    ZoneRepository zoneRepository;


//    @GetMapping("cities/retrieve")
//    public List<Cities> retrieveAllCities(){
//        return citiesRepository.findAll();
//    }

    @GetMapping("/meter/by/facility/{id}")
    public List<Meter> retrieveMeterByFacilityId(@PathVariable long id) throws Exception {
        Optional<Facilities> locationID = facilityRepository.findById(id);

        if (!locationID.isPresent()) {
            throw new Exception("Facility Id is not found: " + id);
        }
        List<Buildings> buildings = locationID.get().getBuildings();
        for (Buildings building : buildings) {
            List<Floor> floors = building.getFloors();
            for (Floor floor : floors) {
                List<Zone> zones = floor.getZones();
                for (Zone zone : zones) {
                    return zone.getMeterType();
                }

            }

        }

        return null;
    }

    @GetMapping("retrieve/meterInformation/from/facilityID/{id}")
    public List<Meter> retreieveMeterInformationFromFacilityId(@PathVariable long id) throws Exception {
        Optional<Facilities> locationID = facilityRepository.findById(id);

        if (!locationID.isPresent()) {
            throw new Exception("Facility Id is not found: " + id);
        } else {
            List<Buildings> buildings = locationID.get().getBuildings();
            for (int i = 0; i < buildings.size(); i++) {
                List<Floor> floors = buildings.get(i).getFloors();
                for (int j = 0; j < floors.size(); j++) {
                    List<Zone> zones = floors.get(j).getZones();
                    for (int k = 0; k < zones.size(); k++) {
                        return zones.get(k).getMeterType();
                    }

                }

            }


        }


        return null;
    }

}
