package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.entities.FloorId;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
import com.augmentolabs.rmzcorp.realestate.response.BuildingResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.FloorServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FloorServiceTest {

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    FloorRepository floorRepository;

    @InjectMocks
    FloorServiceImpl floorService;


//    @Test
//    public void whenGetFloorSuccess() throws IOException {
//        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
//        assertEquals(BuildingResponse.getBuildings().get().getFloors(), floorService.getFloors(1111));
//    }

    @Test
    public void whenGetFloorFailed() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.getFloors(1111));
    }

//    @Test
//    public void whenAddFloorSuccess() throws IOException {
//        when(buildingRepository.findById(anyLong())).thenReturn(BuildingResponse.getBuildings());
//        new Floor().setBuilding(BuildingResponse.getBuildings().get());
//        when(floorRepository.save(new Floor())).thenReturn(new Floor());
//        assertEquals(new Floor(), floorService.addFloor(1111, new Floor()));
//    }

    @Test
    public void whenAddFloorFailed() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.addFloor(1111, new Floor()));
    }

//    @Test
//    public void whenDeleteFloorSuccess() throws IOException {
//        when(floorRepository.findById(new FloorId())).thenReturn(Optional.of(new Floor()));
//        floorRepository.deleteById(new FloorId());
//        assertEquals(, floorService.deleteFloor(new FloorId()));
//    }

    @Test
    public void whenDeleteFloorFailed() throws IOException {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.deleteFloor(new FloorId()));
    }

//    @Test
//    public void whenUpdateFloorSuccess() throws IOException {
//        when(floorRepository.findById(new FloorId())).thenReturn(Optional.of(new Floor()));
//        floorService.deleteFloor(new FloorId());
//        floorService.addFloor(anyLong(), new Floor());
//        assertEquals(new Floor(), floorService.updateFloor(anyLong(), new FloorId(), new Floor()));
//    }

    @Test
    public void whenUpdateFloorFailed() throws IOException {
        when(floorRepository.findById(new FloorId())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->floorService.updateFloor(anyLong(), new FloorId(), new Floor()));
    }
}
