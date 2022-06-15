package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.service.ZoneServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneServiceImpl implements ZoneServices {

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Override
    public Zone getSpecificZone(long zoneId) {
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if (!zone.isPresent()) {
            throw new IdNotFoundException("Zone Id not found: " + zoneId);
        }

        return zone.get();

    }

    @Override
    public Zone saveNewZone(long buildingId, long floorNo, Zone zone) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if(!building.isPresent()){
            throw new IdNotFoundException("Building Id not found");
        }

        List<Floor> floors = building.get().getFloors();
        for(Floor floor:floors){
            if(floor.getFloorNo()==floorNo){
               return zoneRepository.save(zone);
            }
            else {
                throw new IdNotFoundException("Floor No not found");
            }
        }
        return null;
    }

    @Override
    public void deleteZone(long zoneId) {
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if(zone.isPresent()){
            zoneRepository.deleteById(zoneId);
        }
        else {
            throw new IdNotFoundException("ZoneId not found: "+zoneId);
        }

    }

    @Override
    public Zone updateZoneId(long buildingId, long floorNo, long zoneId, Zone zone) {
        Optional<Building> getBuilding = buildingRepository.findById(buildingId);
        Optional<Zone> getZone = zoneRepository.findById(zoneId);
        if(!getBuilding.isPresent()||!getZone.isPresent()){
            throw new IdNotFoundException("BuildingID or ZoneId not found");
        }
        List<Floor> floors = getBuilding.get().getFloors();
        for(Floor floor:floors){
            if(floor.getFloorNo()==floorNo){
                deleteZone(zoneId);
            }
        }
        return saveNewZone(buildingId, floorNo, zone);
    }
}
