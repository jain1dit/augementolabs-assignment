package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.exceptions.LocationNameException;
import com.augmentolabs.rmzcorp.realestate.exceptions.ZoneNameException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ReportControllerTest {

    @Mock
    ZoneRepository zoneRepository;

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    LocationRepository locationRepository;

    private ReportController reportController;

    @Test
    public void whenZoneSuccess() throws Exception {
//        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(zoneRepository.getOne(anyLong())));
//        reportController.retrieveMeterByZoneId(1L);

        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Zone> newZone = zoneRepository.findById(anyLong());
        Assertions.assertFalse(newZone.isPresent());

        Zone zone = new Zone(121, "East", new ArrayList<Meter>(), new Floor());
        Assertions.assertEquals(121, zone.getId());
        Assertions.assertEquals("East", zone.getZoneName());
        Assertions.assertEquals(new ArrayList<Meter>(), zone.getMeterType());
        Assertions.assertEquals(new Floor(), zone.getFloor());

        List<Meter> meters = zone.getMeterType();
        Assertions.assertEquals(new ArrayList<Meter>(), meters);


    }

    @Test
    public void whenBuildingSucess(){
        Optional<Building> building = buildingRepository.findById(anyLong());
        Assertions.assertFalse(building.isPresent());

        Building myBuilding = new Building(12,new ArrayList<Floor>(), new Locations());
        Assertions.assertEquals(121, myBuilding.getId());
        Assertions.assertEquals(new ArrayList<Floor>(), myBuilding.getFloors());
        Assertions.assertEquals(new Locations(), myBuilding.getLocations());

        List<Floor> floors = myBuilding.getFloors();
        for(Floor floor : floors){
            List<Zone> zones = floor.getZones();
            for(Zone zone : zones){
                Assertions.assertEquals(new ArrayList<Meter>(), zone.getMeterType());
            }
        }
    }

    @Test
    public void whenLocationSuccess(){
        Optional<Locations> locations = locationRepository.findById(anyLong());
        Assertions.assertFalse(locations.isPresent());

        Locations myLocation = new Locations(12,new ArrayList<Building>(), "Civil Line", new City());
        Assertions.assertEquals(12, myLocation.getId());
        Assertions.assertEquals(new ArrayList<Building>(), myLocation.getBuildings());
        Assertions.assertEquals("Civil Line", myLocation.getLocationName());
        Assertions.assertEquals(new City(), myLocation.getCity());

        List<Building> buildings = myLocation.getBuildings();
        for(Building building : buildings){
            List<Floor> floors = building.getFloors();
            for(Floor floor : floors){
                List<Zone> zones = floor.getZones();
                for(Zone zone : zones){
                    Assertions.assertEquals(new ArrayList<Meter>(), zone.getMeterType());
                }
            }
        }
    }

    @Test
    public void exceptionTest(){
        ReportController reportController = new ReportController();
            Assertions.assertThrows(RuntimeException.class, ()->reportController.retrieveMeterByZoneId(anyLong()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.retrieveMeterByBuildingId(anyLong()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.retrieveMeterByLocationId(anyLong()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.createCity(new City()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.createLocation(anyLong(), new Locations()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.createBuilding(anyLong(), new Building()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.addFloor(anyLong(), new Floor()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.createZone(anyLong(), anyInt(), new Zone()));
            Assertions.assertThrows(RuntimeException.class, ()->reportController.createMeterByZoneId(anyLong(), new Meter()));


    }


    //Test Validations:

    @ParameterizedTest
    @ValueSource(strings = {"Native4Lance", "BigBazaarRoad", "MallRoad", "Civ1il2Line"})
    public void exceptionLocationNameTest(String locationName){
        Locations locations = new Locations(1, new ArrayList<Building>(), locationName, new City());
        Assertions.assertThrows(LocationNameException.class, ()->ReportController.validateLocationName(locations.getLocationName()));

    }

    @ParameterizedTest
    @ValueSource(strings = {"South", "Souths", "East", "Norths"})
    public void exceptionZoneNameTest(String zoneName){
        Zone zone = new Zone(1, zoneName, new ArrayList<Meter>(), new Floor());
        Assertions.assertThrows(ZoneNameException.class, ()->ReportController.validateZoneName(zone.getZoneName()));

    }

}
