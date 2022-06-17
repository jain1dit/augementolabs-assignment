package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.response.BuildingResponse;
import com.augmentolabs.rmzcorp.realestate.response.LocationResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.BuildingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BuildingServiceTest {

    @Mock
    LocationRepository locationRepository;

    @Mock
    BuildingRepository buildingRepository;

    @InjectMocks
    BuildingServiceImpl buildingService;

    @Test
    public void whenGetAllBuildingSuccess() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        assertEquals(LocationResponse.getLocations().get().getBuildings().iterator().next().getId(), buildingService.getAllBuildings(111).iterator().next().getId());
    }

    @Test
    public void whenGetAllBuildingFailed() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->buildingService.getAllBuildings(111));
    }

    @Test
    public void whenGetSpecificBuildingSuccess() throws IOException{
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        assertEquals(BuildingResponse.getBuildings().get().getId(), buildingService.getSpecificBuilding(1111).getId());
    }

    @Test
    public void whenGetSpecificBuildingFailed() throws IOException{
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->buildingService.getSpecificBuilding(1111));
    }

    @Test
    public void whenSaveNewBuildingSuccess() throws IOException{
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        when(buildingRepository.save(any())).thenReturn(BuildingResponse.getBuildings().get());
        assertEquals(BuildingResponse.getBuildings().get().getId(), buildingService.saveNewBuilding(111, BuildingResponse.getBuildings().get()).getId());
    }

    @Test
    public void whenSaveNewBuildingFailed() throws IOException{
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->buildingService.saveNewBuilding(111, BuildingResponse.getBuildings().get()));
    }

    @Test
    public void whenDeleteBuildingSuccess() throws IOException{
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        doNothing().when(buildingRepository).deleteById(anyLong());
        buildingService.deleteBuilding(1111);
        verify(buildingRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void whenDeleteBuildingFailed() throws IOException{
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->buildingService.deleteBuilding(1111));
    }

    @Test
    public void whenUpdateBuildingSuccess() throws IOException{
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        doNothing().when(buildingRepository).deleteById(anyLong());
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        when(buildingRepository.save(any())).thenReturn(BuildingResponse.getBuildings().get());
        assertEquals(BuildingResponse.getBuildings().get().getId(), buildingService.updateBuilding(111, 1111, BuildingResponse.getBuildings().get()).getId());
    }

    @Test
    public void whenUpdateBuildingFailed() throws IOException{
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()->buildingService.updateBuilding(111, 1111, new Building()));
    }

}
