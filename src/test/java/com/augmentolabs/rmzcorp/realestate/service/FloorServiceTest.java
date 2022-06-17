package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorKey;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
import com.augmentolabs.rmzcorp.realestate.response.BuildingResponse;
import com.augmentolabs.rmzcorp.realestate.response.FloorResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.FloorServiceImpl;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FloorServiceTest {

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    FloorRepository floorRepository;

    @InjectMocks
    FloorServiceImpl floorService;


    @Test
    public void whenGetFloorSuccess() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        assertEquals(BuildingResponse.getBuildings().get().getFloors().iterator().next().getId(), floorService.getFloors(1111).iterator().next().getId());
    }

    @Test
    public void whenGetFloorFailed() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.getFloors(1111));
    }

    @Test
    public void whenAddFloorSuccess() throws Exception {
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        when(floorRepository.save(any())).thenReturn(FloorResponse.getFloor().get());
        assertEquals(FloorResponse.getFloor().get().getId(), floorService.addFloor(1111).getId());
    }

    @Test
    public void whenAddFloorFailed() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.addFloor(1111));
    }

    @Test
    public void whenDeleteFloorSuccess() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        when(floorRepository.findById(any())).thenReturn(FloorResponse.getFloor());
        doNothing().when(floorRepository).deleteById(any());
        floorRepository.deleteById(any());
    }

    @Test
    public void whenDeleteFloorFailed() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.deleteFloor(1111, 5));
    }

    @Test
    public void whenUpdateFloorSuccess() throws Exception {
        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
        when(floorRepository.findById(any())).thenReturn(FloorResponse.getFloor());
        doNothing().when(floorRepository).deleteById(any());
        when(floorRepository.save(any())).thenReturn(FloorResponse.getFloor().get());
        assertEquals(FloorResponse.getFloor().get().getId(), floorService.updateFloor(1111, 5, FloorResponse.getFloor().get()).getId());
    }

    @Test
    public void whenUpdateFloorFailed() throws IOException {
        when(floorRepository.findById(new FloorKey())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.updateFloor(1111, 5, FloorResponse.getFloor().get()));
    }
}
