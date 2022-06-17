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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
        when(cityRepository.save(any())).thenReturn(CityResponse.getCity().orElse(null));
        assertEquals(CityResponse.getCity().orElse(null).getId(), cityService.saveNewCity(CityResponse.getCity().orElse(null)).getId());
    }

    @Test
    public void whenDeleteCitySuccess() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        doNothing().when(cityRepository).deleteById(anyLong());
        cityService.deleteCity(anyLong());
        verify(cityRepository, times(1)).findById(anyLong());
        verify(cityRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void whenDeleteCityFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class , ()-> cityService.deleteCity(anyLong()));
    }

    @Test
    public void whenUpdateCitySuccess() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        doNothing().when(cityRepository).deleteById(anyLong());
        when(cityRepository.save(any())).thenReturn(CityResponse.getCity().orElse(null));

        assertEquals(CityResponse.getCity().orElse(null).getId(), cityService.updateCity(11, CityResponse.getCity().orElse(null)).getId());
    }


    @Test
    public void whenUpdateCityFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->cityService.updateCity(anyLong(), new City()));
    }
}
