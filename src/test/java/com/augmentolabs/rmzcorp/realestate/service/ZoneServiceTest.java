package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.service.impl.ZoneServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ZoneServiceTest {
    @Mock
    ZoneRepository zoneRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    ZoneServiceImpl zoneService;

//    @Test
//    public void whenGetSpecificZoneSuccess(){
//        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
//        assertEquals(new Zone(), zoneService.getSpecificZone(anyLong()));
//    }

    @Test
    public void whenGetSpecificZoneFailed(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneService.getSpecificZone(anyLong()));
    }

//    @Test
//    public void whenSaveNewZoneSuccess(){
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
//        when(zoneRepository.save(new Zone())).thenReturn(new Zone());
//        assertEquals(new Zone(), zoneService.saveNewZone(anyLong(), anyLong(), new Zone()));
//    }

    @Test
    public void whenSaveNewZoneFailed(){
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneService.saveNewZone(anyLong(), anyLong(), new Zone()));
    }

//    @Test
//    public void whenDeleteZoneSuccess(){
//        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
//        zoneRepository.deleteById(anyLong());
//        assertNull(zoneService.deleteZone(anyLong()));
//    }

    @Test
    public void whenDeleteZoneFailed(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneService.deleteZone(anyLong()));
    }

//    @Test
//    public void whenUpdateZoneSuccess(){
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
//        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
//        assertEquals(new Zone(), zoneService.updateZoneId(new Building().getId(), anyLong(), new Zone().getId(), new Zone()));
//    }

    @Test
    public void whenUpdateZoneFailed(){
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()-> zoneService.updateZoneId(new Building().getId(), new Zone().getId(), anyLong(), new Zone()));
    }

}
