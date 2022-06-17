package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.entities.Location;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.response.CityResponse;
import com.augmentolabs.rmzcorp.realestate.response.LocationResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.LocationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class LocationServiceTest {

    @Mock
    CityRepository cityRepository;

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationServiceImpl locationService;

    @Test
    public void whenGetAllLocationSuccess() throws Exception{
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        assertEquals(CityResponse.getCity().get().getLocations().iterator().next().getId(),locationService.getAllLocations(111).iterator().next().getId());

    }

    @Test
    public void whenGetAllLocationFailed(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationService.getAllLocations(11));
    }

    @Test
    public void whenGetSpecificLocationInSpecificCitySuccess() throws Exception{
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        assertEquals(LocationResponse.getLocations().get().getId(), locationService.getSpecificLocationInSpecificCity(11, 111).getId());
    }


    @Test
    public void whenGetSpecificLocationInSpecificCityFailed() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()-> locationService.getSpecificLocationInSpecificCity(11, 111));
    }

    @Test
    public void whenSaveNewLocationSuccess() throws Exception{
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        when(locationRepository.save(any())).thenReturn(LocationResponse.getLocations().get());
        assertEquals(LocationResponse.getLocations().get().getId(), locationService.saveNewLocation(11, LocationResponse.getLocations().get()).getId());
    }

    @Test
    public void whenSaveNewLocationFailed(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()-> locationService.saveNewLocation(11, LocationResponse.getLocations().orElse(null)));
    }

    @Test
    public void whenDeleteLocationSuccess() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        doNothing().when(locationRepository).deleteById(anyLong());
        locationService.deleteLocation(111);
        verify(locationRepository, times(1)).deleteById(111L);
    }

    @Test
    public void whenDeleteLocationFailed() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationService.deleteLocation(111));
    }

    @Test
    public void whenUpdateLocationSuccess() throws IOException{
        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
        doNothing().when(locationRepository).deleteById(anyLong());
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        when(locationRepository.save(any())).thenReturn(LocationResponse.getLocations().get());
        assertEquals(LocationResponse.getLocations().get().getId(), locationService.updateLocation(11, 111, LocationResponse.getLocations().get()).getId());
    }

    @Test
    public void whenUpdateLocationFailed(){
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()-> locationService.updateLocation(11, 111, LocationResponse.getLocations().get()));
    }


}
