package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.response.BuildingResponse;
import com.augmentolabs.rmzcorp.realestate.response.ZoneResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ZoneControllerTest {

    @Mock
    ZoneRepository zoneRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    ZoneController zoneController;

    @Test
    public void whenGetSpecificZoneSuccess() throws Exception {
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
        assertEquals(new Zone(), zoneController.getSpecificZone(anyLong()));
    }

    @Test
    public void whenGetSpecificZoneFailed() throws Exception {
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneController.getSpecificZone(anyLong()));
    }

    @Test
    public void whenSaveNewZoneSuccess() throws Exception {

        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        ZoneResponse.getZones().get().setFloor(BuildingResponse.getBuildings().get().getFloors().get(anyInt()));
        when(zoneRepository.save(ZoneResponse.getZones().get())).thenReturn(ZoneResponse.getZones().get());
        assertEquals(ResponseEntity.ok().build(), zoneController.saveNewZone(anyLong(),anyInt(), ZoneResponse.getZones().get()));
    }

    @Test
    public void whenSaveNewZoneFailed1(){
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(/*IdNotFoundException*/RuntimeException.class, ()-> zoneController.saveNewZone(anyLong(), anyInt(), new Zone()));
    }

    @Test
    public void whenSaveNewZoneFailed2(){
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
        assertThrows(RuntimeException.class, ()-> zoneController.saveNewZone(anyLong(), anyInt(), new Zone()));


    }
}
