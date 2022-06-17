package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.response.MeterResponse;
import com.augmentolabs.rmzcorp.realestate.service.MeterServices;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MeterControllerTest {

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    @Mock
    UriComponents uriComponents;

    @Mock
    ResponseEntity.BodyBuilder bodyBuilder;


    @Mock
    MeterServices meterServices;

    @InjectMocks
    MeterController meterController;

    @Test
    public void whenGetSpecificMeterSuccess() throws IOException {
        when(meterServices.getSpecificMeter(anyLong())).thenReturn(MeterResponse.getMeter().orElse(null));
        assertEquals(ResponseEntity.ok(meterServices.getSpecificMeter(121)), meterController.getSpecificMeter(121));
    }

    @Test
    public void whenSaveNewMeterSuccess() throws Exception {
        when(meterServices.saveNewMeter(anyLong(),any())).thenReturn(MeterResponse.getMeter().orElse(null));

        try (MockedStatic<ServletUriComponentsBuilder> mockUtils = Mockito.mockStatic(ServletUriComponentsBuilder.class)) {
            mockUtils.when(ServletUriComponentsBuilder::fromCurrentRequest).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.path(anyString())).thenReturn(servletUriComponentsBuilder);
            when(servletUriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(mock(UriComponents.class));
            when(uriComponents.toUri()).thenReturn(mock(URI.class));
            try (MockedStatic<ResponseEntity> responseEntityMockedStatic = Mockito.mockStatic(ResponseEntity.class)){
                responseEntityMockedStatic.when(() -> ResponseEntity.created(null)).thenReturn(bodyBuilder);
                when(bodyBuilder.build()).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));
                assertEquals(HttpStatus.CREATED, meterController.saveNewMeter(1000, MeterResponse.getMeter().orElse(null)).getStatusCode());
            }
        }
    }

    @Test
    public void whenDeleteMeterSuccess(){
        doNothing().when(meterServices).deleteMeter(anyLong());
        meterController.deleteMeter(121);
        assertEquals(ResponseEntity.noContent().build(), meterController.deleteMeter(121));
    }

    @Test
    public void whenUpdateMeterSuccess() throws IOException {
        when(meterServices.updateMeter(anyLong(), anyLong(), any())).thenReturn(MeterResponse.getMeter().orElse(null));
        assertEquals(ResponseEntity.ok(meterServices.updateMeter(1000, 121, MeterResponse.getMeter().orElse(null))), meterController.updateMeter(1000, 121, MeterResponse.getMeter().orElse(null)));
    }
}
