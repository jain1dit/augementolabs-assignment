package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.response.CityResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CityControllerTest {

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityController cityController;


    @Test
    public void whenCityResponseSuccess() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        assertEquals(CityResponse.getCity().get(), cityController.getCity(anyLong()));

    }


    @Test
    public void whenCityResponseFailed() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->cityController.getCity(anyLong()));
    }


    @Test
    public void whenCreateCitySuccess() throws Exception {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(cityRepository.save(CityResponse.getCity().get())).thenReturn(CityResponse.getCity().get());
        assertEquals(ResponseEntity.ok().build(), cityController.createCity(CityResponse.getCity().get()));
    }

    @Test
    public void whenCreateCityFailed() throws IOException {
        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
        assertThrows(Exception.class, ()-> cityController.createCity(CityResponse.getCity().get()));
    }
}
