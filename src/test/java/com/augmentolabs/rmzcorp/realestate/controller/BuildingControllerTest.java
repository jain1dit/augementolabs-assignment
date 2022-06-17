package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Building;
import com.augmentolabs.rmzcorp.realestate.response.BuildingResponse;
import com.augmentolabs.rmzcorp.realestate.service.BuildingService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class BuildingControllerTest {

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Mock
    ResponseEntity.BodyBuilder bodyBuilder;


    @Mock
    BuildingService buildingService;

    @InjectMocks
    BuildingController buildingController;

    @Test
    public void whenGetAllBuildingsSuccess(){
        when(buildingService.getAllBuildings(anyLong())).thenReturn(new ArrayList<Building>());
        assertEquals(ResponseEntity.ok(buildingService.getAllBuildings(anyLong())), buildingController.getAllBuildings(anyLong()));
    }

    @Test
    public void whenGetSpecificBuildingSuccess() throws Exception {
        when(buildingService.getSpecificBuilding(anyLong())).thenReturn(BuildingResponse.getBuildings().orElse(null));
        assertEquals(ResponseEntity.ok(buildingService.getSpecificBuilding(1111)), buildingController.getSpecificBuilding(1111));
    }

    @Test
    public void whenSaveNewBuildingSuccess() throws Exception {
        when(buildingService.saveNewBuilding(anyLong(), any())).thenReturn(BuildingResponse.getBuildings().orElse(null));

        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(mock(UriComponents.class));
            when(uriComponents.toUri()).thenReturn(mock(URI.class));
            try (MockedStatic<ResponseEntity> responseEntityMockedStatic = Mockito.mockStatic(ResponseEntity.class)){
                responseEntityMockedStatic.when(() -> ResponseEntity.created(null)).thenReturn(bodyBuilder);
                when(bodyBuilder.build()).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
                assertEquals(HttpStatus.CREATED, buildingController.saveNewBuilding(111, BuildingResponse.getBuildings().orElse(null)).getStatusCode());
            }
        }
    }


    @Test
    public void whenDeleteBuildingSuccess(){
        doNothing().when(buildingService).deleteBuilding(anyLong());
        buildingController.deleteBuilding(1111);
        verify(buildingService, times(1)).deleteBuilding(anyLong());
    }

    @Test
    public void whenUpdateBuildingSuccess() throws IOException {
        when(buildingService.updateBuilding(anyLong(), anyLong(), any())).thenReturn(BuildingResponse.getBuildings().orElse(null));
        assertEquals(ResponseEntity.ok(buildingService.updateBuilding(111, 1111, BuildingResponse.getBuildings().orElse(null))), buildingController.updateBuilding(111, 1111, BuildingResponse.getBuildings().orElse(null)));
    }

}
