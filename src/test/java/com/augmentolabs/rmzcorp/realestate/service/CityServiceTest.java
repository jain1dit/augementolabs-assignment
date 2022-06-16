package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.response.CityResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CityServiceTest {

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityServiceImpl cityService;

    @Test
    public void whenGetAllCitiesSuccess() throws IOException {
        when(cityRepository.findAll()).thenReturn(new ArrayList<City>());
        assertEquals(new ArrayList<City>(), cityService.getAllCities());
    }

    @Test
    public void whenGetCityByIDSuccess() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));

        assertEquals(new City(), cityService.getCityById(anyLong()));
    }

    @Test
    public void whenGetCityByIDFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->cityService.getCityById(anyLong()));
    }

    @Test
    public void whenSaveNewCitySuccess() throws IOException {
        when(cityRepository.save(new City())).thenReturn(new City());
        assertEquals(new City(), cityService.saveNewCity(new City()));
    }

//    @Test
//    public void whenDeleteCitySuccess() throws IOException {
//        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
//        assertEquals(, cityService.deleteCity(anyLong()));
//    }

    @Test
    public void whenDeleteCityFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class , ()-> cityService.deleteCity(anyLong()));
    }

//    @Test
//    public void whenUpdateCitySuccess() throws Exception {
//        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City()));
//        cityService.deleteCity(new City().getId());
//        cityService.saveNewCity(new City());
//        assertEquals(new City(), cityService.updateCity(new City().getId(), new City()));
//    }


    @Test
    public void whenUpdateCityFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->cityService.updateCity(anyLong(), new City()));
    }
}
