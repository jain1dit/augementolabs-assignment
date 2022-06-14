package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Meter;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.MeterRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MeterControllerTest {

    @Mock
    ZoneRepository zoneRepository;

    @Mock
    MeterRepository meterRepository;

    @InjectMocks
    MeterController meterController;

    @Test
    public void whenGetSpecificMeterSuccess(){
        when(meterRepository.findById(anyLong())).thenReturn(Optional.of(new Meter()));
        assertEquals(new Meter(), meterController.getSpecificMeter(anyLong()));

    }

    @Test
    public void whenGetSpecificMeterFailed(){
        when(meterRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->meterController.getSpecificMeter(anyLong()));

    }

    @Test
    public void whenSaveNewMeterSuccess() throws Exception {
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
        when(meterRepository.findById(anyLong())).thenReturn(Optional.empty());
        new Meter().setZone(new Zone());
        when(meterRepository.save(new Meter())).thenReturn(new Meter());
        assertEquals(ResponseEntity.ok().build(), meterController.saveNewMeter(anyLong(), new Meter()));

    }

    @Test
    public void whenSaveNewMeterFailed1(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, ()->meterController.saveNewMeter(anyLong(), new Meter()));

    }

    @Test
    public void whenSaveNewMeterFailed2(){
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(new Zone()));
        when(meterRepository.findById(anyLong())).thenReturn(Optional.of(new Meter()));
        assertThrows(RuntimeException.class, ()->meterController.saveNewMeter(anyLong(), new Meter()));

    }
}
