package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Override
    public List<Building> getAllBuildings(long locationId) {
        Optional<Locations> locations = locationRepository.findById(locationId);
        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location ID not found: " + locationId);
        }

        return locations.get().getBuildings();
    }

    @Override
    public Building getSpecificBuilding(long buildingId) {
        Optional<Building> buildings = buildingRepository.findById(buildingId);

        if (!buildings.isPresent()) {
            throw new IdNotFoundException("Facility Id is not found: " + buildingId);
        }

        return buildings.get();
    }

    @Override
    public Building saveNewBuilding(long locationId, Building building) {
        Optional<Locations> locations = locationRepository.findById(locationId);
        if (!locations.isPresent()) {
            throw new IdNotFoundException("Location Id not found" + locationId);
        }
        building.setLocations(locations.get());
        return buildingRepository.save(building);
    }

    @Override
    public void deleteBuilding(long buildingId) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if(!building.isPresent()){
            throw new IdNotFoundException("Building ID not found: "+ buildingId);
        }
        buildingRepository.deleteById(buildingId);
    }

    @Override
    public Building updateBuilding(long locationId, long buildingId, Building building) {
        Optional<Building> deleteBuilding = buildingRepository.findById(buildingId);
        if(deleteBuilding.isPresent()){
           deleteBuilding(buildingId);
        }
       return saveNewBuilding(locationId, building);
    }
}
