package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FloorService {
    List<Floor> getFloors(long buildingId);

    Floor addFloor(long buildingId,Floor floor);

    void deleteFloor(FloorId floorId);

    Floor updateFloor(long buildingId, FloorId floorId, Floor floor);
}
