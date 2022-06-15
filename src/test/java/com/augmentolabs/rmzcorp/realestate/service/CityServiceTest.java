package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.response.CityResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Mock
    CityServiceImpl cityService;



    @Test
    public void whenGetAllCitiesSuccess() throws IOException {
        when(cityRepository.findAll()).thenReturn(new ArrayList<City>());
        assertEquals(new ArrayList<City>(), cityService.getAllCities());
    }

    @Test
    public void whenGetCityByIDSuccess() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn((CityResponse.getCity()));

        assertEquals(CityResponse.getCity().get(), cityService.getCityById(anyLong()));
    }

    @Test
    public void whenGetCityByIDFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->cityService.getCityById(anyLong()));
    }

    @Test
    public void whenSaveNewCitySuccess() throws IOException {
        when(cityRepository.save(CityResponse.getCity().get())).thenReturn(CityResponse.getCity().get());
        assertEquals(CityResponse.getCity().get(), cityService.saveNewCity(CityResponse.getCity().get()));
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

    @Test
    public void whenUpdateCitySuccess() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        cityService.deleteCity(CityResponse.getCity().get().getId());
        cityService.saveNewCity(new City());
        assertEquals(new City(), cityService.updateCity(anyLong(), new City()));
    }


    @Test
    public void whenUpdateCityFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->cityService.updateCity(anyLong(), new City()));
    }
}
