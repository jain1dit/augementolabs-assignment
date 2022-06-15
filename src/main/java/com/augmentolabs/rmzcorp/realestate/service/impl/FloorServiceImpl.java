package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorId;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
import com.augmentolabs.rmzcorp.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    FloorRepository floorRepository;

    @Override
    public List<Floor> getFloors(long buildingId) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (!building.isPresent()) {
            throw new IdNotFoundException("Floor Id not found: " + buildingId);
        }
        return building.get().getFloors();
    }

    @Override
    public Floor addFloor(long buildingId, Floor floor) {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (!building.isPresent()) {
            throw new IdNotFoundException("Building ID not found: " + buildingId);
        }

        floor.setBuilding(building.get());
        return floorRepository.save(floor);
    }

    @Override
    public void deleteFloor(FloorId floorId) {
        Optional<Floor> floor = floorRepository.findById(floorId);
        if (!floor.isPresent()) {
            throw new IdNotFoundException("Floor No. not present: " + floorId);
        }
        floorRepository.deleteById(floorId);


    }

    @Override
    public Floor updateFloor(long buildingId, FloorId floorId, Floor floor) {
        Optional<Floor> availableFloor = floorRepository.findById(floorId);
        if (!availableFloor.isPresent()) {
            throw new IdNotFoundException("Floor NO. or Building Id not found");
        }

        deleteFloor(floorId);

        return addFloor(buildingId, floor);
    }
}
