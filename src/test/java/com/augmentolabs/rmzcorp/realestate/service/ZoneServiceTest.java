package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.response.BuildingResponse;
import com.augmentolabs.rmzcorp.realestate.response.ZoneResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.ZoneServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ZoneServiceTest {
    @Mock
    ZoneRepository zoneRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    ZoneServiceImpl zoneService;

    @Test
    public void whenGetSpecificZoneSuccess() throws IOException {
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        assertEquals(ZoneResponse.getZones().get().getId(), zoneService.getSpecificZone(1000).getId());
    }

    @Test
    public void whenGetSpecificZoneFailed(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneService.getSpecificZone(1000));
    }

    @Test
    public void whenSaveNewZoneSuccess() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        when(zoneRepository.save(any())).thenReturn(ZoneResponse.getZones().get());
        assertEquals(ZoneResponse.getZones().get().getId(), zoneService.saveNewZone(1111, 5, ZoneResponse.getZones().orElse(null)).getId());
    }

    @Test
    public void whenSaveNewZoneFailed(){
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneService.saveNewZone(1111, 5, ZoneResponse.getZones().orElse(null)));
    }

    @Test
    public void whenDeleteZoneSuccess() throws IOException {
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        doNothing().when(zoneRepository).deleteById(anyLong());
        zoneRepository.deleteById(1000L);

    }

    @Test
    public void whenDeleteZoneFailed(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->zoneService.deleteZone(1000));
    }

    @Test
    public void whenUpdateZoneSuccess() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        doNothing().when(zoneRepository).deleteById(anyLong());
        when(zoneRepository.save(any())).thenReturn(ZoneResponse.getZones().get());
        assertEquals(ZoneResponse.getZones().get().getId(), zoneService.updateZoneId(1111, 5, 1000, ZoneResponse.getZones().orElse(null)).getId());
    }

    @Test
    public void whenUpdateZoneFailed(){
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()-> zoneService.updateZoneId(1111, 5, 1000, ZoneResponse.getZones().orElse(null)));
    }

}
