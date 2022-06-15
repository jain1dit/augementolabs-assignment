package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FloorService {
    List<Floor> getFloors(long buildingId);

    Floor addFloor(long buildingId,Floor floor);

    void deleteFloor(long buildingId, long floorNo);

    Floor updateFloor(long buildingId, long floorNo, Floor floor);
}
