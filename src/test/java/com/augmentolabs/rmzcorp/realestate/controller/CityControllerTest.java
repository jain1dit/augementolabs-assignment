//package com.augmentolabs.rmzcorp.realestate.controller;
//
//import com.augmentolabs.rmzcorp.realestate.entities.City;
//import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
//import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
//import com.augmentolabs.rmzcorp.realestate.response.CityResponse;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import java.io.IOException;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.Optional;
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//import org.springframework.web.util.UriComponents;
//
//@RunWith(SpringRunner.class)
//public class CityControllerTest {
//
//    @Mock
//    CityRepository cityRepository;
//
//    @Mock
//    ServletUriComponentsBuilder servletUriComponentsBuilder;
//
////    @Mock
////    UriComponents uriComponents;
////
////    @Mock
////    URI uri;
//
//    @InjectMocks
//    CityController cityController;
//
//    @Test
//    public void whenAllCityResponseSuccess() throws IOException {
//        when(cityRepository.findAll()).thenReturn(new ArrayList<City>());
//        assertEquals(new ArrayList<City>(), cityController.getAllCity());
//    }
//
//
//    @Test
//    public void whenCityResponseSuccess() throws Exception {
//        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
//        assertEquals(CityResponse.getCity().get(), cityController.getSpecificCity(anyLong()));
//
//    }
//
//
//    @Test
//    public void whenCityResponseFailed() throws Exception {
//        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());
//        assertThrows(IdNotFoundException.class, () -> cityController.getSpecificCity(anyLong()));
//    }
//
//
//    @Test
//    public void whenSaveNewCitySuccess() throws Exception {
//        when(cityRepository.save(CityResponse.getCity().get())).thenReturn(CityResponse.getCity().get());
//
//        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
//            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
//            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
////            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(uriComponents);
////            when(uriComponents.toUri()).thenReturn(uri);
//            assertEquals(CityResponse.getCity(), cityController.saveNewCity(CityResponse.getCity().get()).getBody());
//        }
//
//    }
//
//    @Test
//    public void whenSaveNewCityFailed() throws IOException {
//        when(cityRepository.findById(anyLong())).thenReturn(CityResponse.getCity());
//        assertThrows(Exception.class, () -> cityController.saveNewCity(CityResponse.getCity().get()));
//    }
//
////    @ParameterizedTest
////    @ValueSource(strings = {"India", "Canada", "Australia4"})
////    public void checkCountryName(String countryName){
////        City city = new City(10, countryName, "State", new ArrayList<Locations>());
////    }
//}
