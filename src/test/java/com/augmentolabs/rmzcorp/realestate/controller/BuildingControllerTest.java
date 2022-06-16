package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.entities.Location;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.response.LocationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BuildingControllerTest {

    @Mock
    LocationRepository locationRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    BuildingController buildingController;

    @Test
    public void whenGetAllBuildingsSuccess() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        assertEquals(LocationResponse.getLocations().get().getBuildings(), buildingController.getAllBuildings(anyLong()));
    }


    @Test
    public void whenGetAllBuildingsFailed() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->buildingController.getAllBuildings(anyLong()));
    }

    @Test
    public void whenGetSpecificBuildingSuccess() throws Exception {
        when(buildingRepository.findById(any())).thenReturn(Optional.of(new Building()));
        assertEquals(new Building(), buildingController.getSpecificBuilding(anyLong()));
    }

    @Test
    public void whenGetSpecificBuildingFailed() throws Exception {
        when(buildingRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->buildingController.getSpecificBuilding(anyLong()));
    }

    @Test
    public void whenSaveNewBuildingSuccess(){
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(new Location()));
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(buildingRepository.save(new Building())).thenReturn(new Building());
        assertEquals(ResponseEntity.ok().build(), buildingController.saveNewBuilding(anyLong(), new Building()));
    }

    @Test
    public void whenSaveNewBuildingFailed1(){
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()-> buildingController.saveNewBuilding(anyLong(), new Building()));
    }


    @Test
    public void whenSaveNewBuildingFailed2(){
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(new Location()));
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
        assertThrows(RuntimeException.class, ()-> buildingController.saveNewBuilding(anyLong(), new Building()));
    }

}
