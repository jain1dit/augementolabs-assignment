package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Zone;

public interface ZoneServices {

  Zone getSpecificZone(long zoneId);

  Zone saveNewZone(long buildingId, long floorNo, Zone zone);

  void deleteZone(long zoneId);

  Zone updateZoneId(long buildingId, long floorNo, long ZoneId, Zone zone);
}
