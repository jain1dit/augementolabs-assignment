package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorKey;

import java.util.List;

public interface FloorService {
    List<Floor> getFloors(long buildingId);

    Floor addFloor(long buildingId) throws Exception;

    void deleteFloor(long buildingId, long floorId);

    Floor updateFloor(long buildingId, long floorId, Floor floor) throws Exception;
}
