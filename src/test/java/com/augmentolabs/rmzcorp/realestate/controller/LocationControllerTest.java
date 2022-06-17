package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Location;
import com.augmentolabs.rmzcorp.realestate.response.LocationResponse;
import com.augmentolabs.rmzcorp.realestate.service.LocationService;
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
public class LocationControllerTest {

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Mock
    ResponseEntity.BodyBuilder bodyBuilder;


    @Mock
    LocationService locationService;

    @InjectMocks
    LocationController locationController;



    @Test
    public void whenGetAllLocationSuccess(){
        when(locationService.getAllLocations(anyLong())).thenReturn(new ArrayList<Location>());
        assertEquals(ResponseEntity.ok(locationService.getAllLocations(anyLong())), locationController.getAllLocations(anyLong()));
    }

    @Test
    public void whenGetSpecificLocationInSpecificCitySuccess() throws Exception {
        when(locationService.getSpecificLocationInSpecificCity(anyLong(), anyLong())).thenReturn(LocationResponse.getLocations().orElse(null));
        assertEquals(ResponseEntity.ok(locationService.getSpecificLocationInSpecificCity(11, 111)), locationController.getSpecificLocationInSpecificCity(11, 111));
    }

    @Test
    public void whenSaveNewLocationSuccess() throws Exception {
        when(locationService.saveNewLocation(anyLong(), any())).thenReturn(LocationResponse.getLocations().orElse(null));

        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(mock(UriComponents.class));
            when(uriComponents.toUri()).thenReturn(mock(URI.class));
            try (MockedStatic<ResponseEntity> responseEntityMockedStatic = Mockito.mockStatic(ResponseEntity.class)){
                responseEntityMockedStatic.when(() -> ResponseEntity.created(null)).thenReturn(bodyBuilder);
                when(bodyBuilder.build()).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
                assertEquals(HttpStatus.CREATED, locationController.saveNewLocation(11,LocationResponse.getLocations().orElse(null)).getStatusCode());
            }
        }
    }


    @Test
    public void whenDeleteLocationSuccess(){
        doNothing().when(locationService).deleteLocation(anyLong());
        locationController.deleteLocation(111);
        verify(locationService, times(1)).deleteLocation(111);
    }

    @Test
    public void whenUpdateLocationSuccess() throws IOException {
        when(locationService.updateLocation(anyLong(), anyLong(), any())).thenReturn(LocationResponse.getLocations().orElse(null));
        assertEquals(ResponseEntity.ok(locationService.updateLocation(11,111, LocationResponse.getLocations().orElse(null))), locationController.updateLocation(11,111, LocationResponse.getLocations().orElse(null)));
    }

}
