package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.MeterRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.response.MeterResponse;
import com.augmentolabs.rmzcorp.realestate.response.ZoneResponse;
import com.augmentolabs.rmzcorp.realestate.service.impl.MeterServicesImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class MeterServiceTest {

    @Mock
    MeterRepository meterRepository;

    @Mock
    ZoneRepository zoneRepository;

    @InjectMocks
    MeterServicesImpl meterServices;


    @Test
    public void whenGetSpecificMeterSuccess() throws IOException {
        when(meterRepository.findById(anyLong())).thenReturn(MeterResponse.getMeter());
        assertEquals(MeterResponse.getMeter().orElse(null).getId(), meterServices.getSpecificMeter(121).getId());
    }

    @Test
    public void whenGetSpecificMeterFailed(){
        when(meterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->meterServices.getSpecificMeter(anyLong()));
    }

    @Test
    public void whenSaveNewMeterSuccess() throws IOException {
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        when(meterRepository.save(any())).thenReturn(MeterResponse.getMeter().get());
        assertEquals(MeterResponse.getMeter().orElse(null).getId(), meterServices.saveNewMeter(1000, MeterResponse.getMeter().orElse(null)).getId());
    }

    @Test
    public void whenSaveNewMeterFailed(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->meterServices.saveNewMeter(anyLong(), MeterResponse.getMeter().orElse(null)));
    }

    @Test
    public void whenDeleteMeterSuccess() throws IOException {
        when(meterRepository.findById(anyLong())).thenReturn(MeterResponse.getMeter());
        doNothing().when(meterRepository).deleteById(anyLong());
        meterServices.deleteMeter(121);
        verify(meterRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void whenDeleteMeterFailed() throws IOException {
        when(meterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->meterServices.deleteMeter(anyLong()));
    }

    @Test
    public void whenUpdateMeterSuccess() throws IOException {
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        when(meterRepository.findById(anyLong())).thenReturn(MeterResponse.getMeter());
        doNothing().when(meterRepository).deleteById(anyLong());
        when(meterRepository.save(any())).thenReturn(MeterResponse.getMeter().get());
        assertEquals(MeterResponse.getMeter().orElse(null).getId(), meterServices.updateMeter(1000, 121, MeterResponse.getMeter().orElse(null)).getId());
    }

    @Test
    public void whenUpdateMeterFailed() throws IOException {
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->meterServices.updateMeter(1000, 121, MeterResponse.getMeter().orElse(null)).getId());
    }
}
