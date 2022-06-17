package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.response.CityResponse;
import com.augmentolabs.rmzcorp.realestate.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CityControllerTest {

    @Mock
    CityService cityService;

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Mock
    ResponseEntity.BodyBuilder bodyBuilder;

    @InjectMocks
    CityController cityController;



    @Test
    public void whenGetAllCitySuccess(){
        when(cityService.getAllCities()).thenReturn(new ArrayList<City>());
        assertEquals(ResponseEntity.ok(cityService.getAllCities()), cityController.getAllCity());
    }

    @Test
    public void whenGetCityById() throws IOException {
        when(cityService.getCityById(anyLong())).thenReturn(CityResponse.getCity().orElse(null));
        assertEquals(ResponseEntity.ok(cityService.getCityById(anyLong())), cityController.getCityById(11));
    }

    @Test
    public void whenSaveNewCitySuccess() throws Exception {
        when(cityService.saveNewCity(any())).thenReturn(CityResponse.getCity().orElse(null));

        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(mock(UriComponents.class));
            when(uriComponents.toUri()).thenReturn(mock(URI.class));
            try (MockedStatic<ResponseEntity> responseEntityMockedStatic = Mockito.mockStatic(ResponseEntity.class)){
                responseEntityMockedStatic.when(() -> ResponseEntity.created(null)).thenReturn(bodyBuilder);
                when(bodyBuilder.build()).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
                assertEquals(HttpStatus.CREATED, cityController.saveNewCity(CityResponse.getCity().orElse(null)).getStatusCode());
            }
        }
    }

    @Test
    public void whenUpdateCitySuccess() throws IOException {
        when(cityService.updateCity(anyLong(), any())).thenReturn(CityResponse.getCity().orElse(null));
        assertEquals(ResponseEntity.ok(cityService.updateCity(11, CityResponse.getCity().orElse(null))), cityController.updateCity(11, CityResponse.getCity().orElse(null)));
    }

    @Test
    public void whenDeleteCitySuccess() throws Exception {
        doNothing().when(cityService).deleteCity(anyLong());
        cityController.deleteCity(11);
        verify(cityService, times(1)).deleteCity(anyLong());
    }

}
