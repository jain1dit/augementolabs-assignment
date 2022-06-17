package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.response.ZoneResponse;
import com.augmentolabs.rmzcorp.realestate.service.ZoneServices;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ZoneControllerTest {

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Mock
    ResponseEntity.BodyBuilder bodyBuilder;


    @Mock
    ZoneServices zoneServices;

    @InjectMocks
    ZoneController zoneController;

    @Test
    public void whenGetSpeciicZonSuccess() throws Exception {
        when(zoneServices.getSpecificZone(anyLong())).thenReturn(ZoneResponse.getZones().orElse(null));
        assertEquals(ResponseEntity.ok(zoneServices.getSpecificZone(1000)), zoneController.getSpecificZone(1000));
    }

    @Test
    public void whenSaveNewZoneSuccess() throws Exception {
        when(zoneServices.saveNewZone(anyLong(), anyLong(), any())).thenReturn(ZoneResponse.getZones().orElse(null));

        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(mock(UriComponents.class));
            when(uriComponents.toUri()).thenReturn(mock(URI.class));
            try (MockedStatic<ResponseEntity> responseEntityMockedStatic = Mockito.mockStatic(ResponseEntity.class)){
                responseEntityMockedStatic.when(() -> ResponseEntity.created(null)).thenReturn(bodyBuilder);
                when(bodyBuilder.build()).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
                assertEquals(HttpStatus.CREATED, zoneController.saveNewZone(1111, 5, ZoneResponse.getZones().orElse(null)).getStatusCode());
            }
        }
    }


    @Test
    public void whenDeleteZoneSuccess(){
        doNothing().when(zoneServices).deleteZone(anyLong());
        zoneController.deleteZone(1000);
        assertEquals(ResponseEntity.noContent().build(), zoneController.deleteZone(1000));
    }

    @Test
    public void whenUpdateZoneSuccess() throws IOException {
        when(zoneServices.updateZoneId(anyLong(), anyLong(), anyLong(), any())).thenReturn(ZoneResponse.getZones().orElse(null));
        assertEquals(ResponseEntity.ok(zoneServices.updateZoneId(1111,5,1000, ZoneResponse.getZones().orElse(null))), zoneController.updateZone(1111, 5, 1000, ZoneResponse.getZones().orElse(null)));
    }
}
