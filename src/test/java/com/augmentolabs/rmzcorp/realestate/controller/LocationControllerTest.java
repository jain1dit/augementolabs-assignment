package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Locations;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Id;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class LocationControllerTest {

    @Mock
    CityRepository cityRepository;

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationController locationController;

    @Test
    public void whenGetAllLocationSuccess(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
        assertEquals(new City().getLocations(), locationController.getAllLocations(anyLong()));
    }

    @Test
    public void whenGetAllLocationFailed(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationController.getAllLocations(anyLong()));
    }

    @Test
    public void whenGetSpecificLocationSuccess() throws Exception {
        when(locationRepository.findById(any())).thenReturn(Optional.of(new Locations()));
        assertEquals(new Locations(), locationController.getSpecificLocation(anyLong()));
    }

    @Test
    public void whenGetSpecificLocationFailed() throws Exception {
        when(locationRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationController.getSpecificLocation(anyLong()));
    }

    @Test
    public void whenSaveNewLocationSuccess() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertEquals(ResponseEntity.ok().build(), locationController.saveNewLocation(anyLong(), new Locations()));
    }

    @Test
    public void whenSaveNewLocationFailed1(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationController.saveNewLocation(anyLong(), new Locations()));
    }

    @Test
    public void whenSaveNewLocationFailed2(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(new Locations()));
        assertThrows(RuntimeException.class, ()->locationController.saveNewLocation(anyLong(), new Locations()));
    }

}
