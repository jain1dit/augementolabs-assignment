package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BuildingService {
    List<Building> getAllBuildings(long locationId);

    Building getSpecificBuilding(long buildingId);

    Building saveNewBuilding(long locationId,Building building);

    void deleteBuilding(long buildingId);

    Building updateBuilding(long locationId, long buildingId, Building building);
}
