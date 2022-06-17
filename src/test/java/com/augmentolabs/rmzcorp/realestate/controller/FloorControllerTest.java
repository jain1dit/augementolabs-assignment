package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Floor;
import com.augmentolabs.rmzcorp.realestate.response.FloorResponse;
import com.augmentolabs.rmzcorp.realestate.service.FloorService;
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

import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FloorControllerTest {

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Mock
    ResponseEntity.BodyBuilder bodyBuilder;

    @Mock
    FloorService floorService;

    @InjectMocks
    FloorController floorController;

    @Test
    public void whenGetFloorsSuccess(){
        when(floorService.getFloors(anyLong())).thenReturn(new ArrayList<Floor>());
        assertEquals(ResponseEntity.ok(floorService.getFloors(1111)), floorController.getFloors(1111));
    }

    @Test
    public void whenSaveNewFloorSuccess() throws Exception {
        when(floorService.addFloor(anyLong())).thenReturn(FloorResponse.getFloor().orElse(null));

        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(mock(UriComponents.class));
            when(uriComponents.toUri()).thenReturn(mock(URI.class));
            try (MockedStatic<ResponseEntity> responseEntityMockedStatic = Mockito.mockStatic(ResponseEntity.class)){
                responseEntityMockedStatic.when(() -> ResponseEntity.created(null)).thenReturn(bodyBuilder);
                when(bodyBuilder.build()).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
                assertEquals(HttpStatus.CREATED, floorController.addFloor(1111).getStatusCode());
            }
        }
    }


    @Test
    public void whenDeleteFloorSuccess(){
        doNothing().when(floorService).deleteFloor(anyLong(), anyLong());
        floorController.deleteFloor(1111, 5);
        assertEquals(ResponseEntity.noContent().build(), floorController.deleteFloor(1111, 5));
    }

    @Test
    public void whenUpdateFloorSuccess() throws Exception {
        when(floorService.updateFloor(anyLong(), anyLong(), any())).thenReturn(FloorResponse.getFloor().orElse(null));
        assertEquals(ResponseEntity.ok(floorService.updateFloor(1111,5, FloorResponse.getFloor().orElse(null))), floorController.updateFloor(1111,5, FloorResponse.getFloor().orElse(null) ));
    }
}
