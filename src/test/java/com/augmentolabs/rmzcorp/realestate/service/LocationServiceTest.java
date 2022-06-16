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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
        assertEquals(new City().getLocations(),locationService.getAllLocations(anyLong()));

    }

    @Test
    public void whenGetAllLocationFailed(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationService.getAllLocations(anyLong()));
    }

//    @Test
//    public void whenGetSpecificLocationInSpecificCitySuccess() throws Exception{
//        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
//        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations());
//        assertEquals(LocationResponse.getLocations().get(), locationService.getSpecificLocationInSpecificCity(11, 111));
//    }


    @Test
    public void whenGetSpecificLocationInSpecificCityFailed() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()-> locationService.getSpecificLocationInSpecificCity(anyLong(), anyLong()));
    }

//    @Test
//    public void whenSaveNewLocationSuccess() throws Exception{
//        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
//        LocationResponse.getLocations().get().setCity(CityResponse.getCity().get());
//        when(locationRepository.save(LocationResponse.getLocations().get())).thenReturn(LocationResponse.getLocations().get());
//        assertEquals(LocationResponse.getLocations().get(), locationService.saveNewLocation(11, LocationResponse.getLocations().get()));
//    }

    @Test
    public void whenSaveNewLocationFailed(){
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()-> locationService.saveNewLocation(LocationResponse.getLocations().get().getId(), new Location()));
    }

//    @Test
//    public void whenDeleteLocationSuccess() throws IOException {
//        when(locationRepository.findById(anyLong())).thenReturn(LocationResponse.getLocations()).thenReturn(Optional.empty());
//        assertEquals(, locationService.deleteLocation(LocationResponse.getLocations().get().getId()));
//    }

    @Test
    public void whenDeleteLocationFailed() throws IOException {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->locationService.deleteLocation(LocationResponse.getLocations().get().getId()));
    }

//    @Test
//    public void whenUpdateLocationSuccess() throws IOException{
//        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(new Location()));
//        locationService.deleteLocation(new Location().getId());
//        locationService.saveNewLocation(CityResponse.getCity().get().getId(), LocationResponse.getLocations().get());
//        assertEquals(LocationResponse.getLocations().get(), locationService.updateLocation(CityResponse.getCity().get().getId(), 111, LocationResponse.getLocations().get()));
//    }

    @Test
    public void whenUpdateLocationFailed(){
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()-> locationService.updateLocation(CityResponse.getCity().get().getId(), LocationResponse.getLocations().get().getId(), new Location()));
    }


}
