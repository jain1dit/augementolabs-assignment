package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ZoneServices {

    Zone getSpecificZone(long zoneId);

    Zone saveNewZone( long buildingId,long floorNo, Zone zone);

    void deleteZone(long zoneId);

    Zone updateZoneId(long buildingId, long floorNo, long ZoneId, Zone zone );
}
