package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorKey;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
import com.augmentolabs.rmzcorp.realestate.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Floor addFloor(long buildingId) throws Exception {
        Optional<Building> building = buildingRepository.findById(buildingId);
        if (!building.isPresent()) {
            throw new IdNotFoundException("Building ID not found: " + buildingId);
        }

        List<Floor> allFloors = building.get().getFloors();
        Floor floor = new Floor();
        int setFloorNo = allFloors.size();
        floor.setFloorNumber(++setFloorNo);
        floor.setBuildingId(buildingId);
        floor.setBuilding(building.get());
        return floorRepository.save(floor);
    }

    @Override
    public void deleteFloor(long buildingId, long floorId) {

        Optional<Building> getBuilding = buildingRepository.findById(buildingId);
        if(!getBuilding.isPresent()){
            throw new IdNotFoundException("Building Id not found "+ buildingId);
        }

        FloorKey floorKey = new FloorKey();
        floorKey.setId(floorId);
        floorKey.setBuildingId(getBuilding.get().getId());
        Optional<Floor> floor = floorRepository.findById(floorKey);
        if (!floor.isPresent()) {
            throw new IdNotFoundException("Floor No. not present: " + floorId);
        }
        floorRepository.deleteById(floorKey);


    }

    @Override
    public Floor updateFloor(long buildingId, long floorId, Floor floor) throws Exception {
        deleteFloor(buildingId, floorId);
        return addFloor(buildingId);
    }
}
