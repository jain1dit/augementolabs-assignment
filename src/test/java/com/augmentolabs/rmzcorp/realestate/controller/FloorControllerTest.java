//package com.augmentolabs.rmzcorp.realestate.controller;
//
//import com.augmentolabs.rmzcorp.realestate.entities.Building;
//import com.augmentolabs.rmzcorp.realestate.entities.Floor;
//import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
//import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
//import com.augmentolabs.rmzcorp.realestate.repositories.FloorRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//public class FloorControllerTest {
//
//    @Mock
//    BuildingRepository buildingRepository;
//
//    @Mock
//    FloorRepository floorRepository;
//
//    @InjectMocks
//    FloorController floorController;
//
//    @Test
//    public void whenGetFloorsSuccess(){
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
//        assertEquals(new Building().getFloors(), floorController.getFloors(anyLong()));
//    }
//
//    @Test
//    public void whenGetFloorsFailed(){
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(IdNotFoundException.class, ()->floorController.getFloors(anyLong()));
//    }
//
//    @Test
//    public void whenAddFloorSuccess()  {
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
//        when(floorRepository.findById(anyLong())).thenReturn(Optional.empty());
//        when(floorRepository.save(new Floor())).thenReturn(new Floor());
//        assertEquals(ResponseEntity.ok().build(), floorController.addFloor(anyLong(), new Floor()));
//    }
//
//    @Test
//    public void whenAddFloorFailed1()  {
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(IdNotFoundException.class, ()->floorController.addFloor(anyLong(), new Floor()));
//    }
//
//    @Test
//    public void whenAddFloorFailed2()  {
//        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(new Building()));
//        when(floorRepository.findById(anyLong())).thenReturn(Optional.of(new Floor()));
//        assertThrows(RuntimeException.class, ()->floorController.addFloor(anyLong(), new Floor()));
//    }
//
//
//}
