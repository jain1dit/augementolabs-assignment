package com.augmentolabs.rmzcorp.realestate.controllerTest;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.exceptions.LocationNameException;
import com.augmentolabs.rmzcorp.realestate.exceptions.ZoneNameException;
import com.augmentolabs.rmzcorp.realestate.repositories.BuildingRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.response.ZoneResponse;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ReportControllerTest {

    @Mock
    ZoneRepository zoneRepository;

    @Mock
    BuildingRepository buildingRepository;

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    private ZoneController zoneController;

    @Test
    public void whenZoneSuccess() throws Exception {
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        assertEquals(ZoneResponse.getZones().get(), zoneController.retrieveMeterByZoneId(1L));
    }

    @Test
    public void whenZoneFailure() throws Exception {
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> zoneController.retrieveMeterByZoneId(1L));
    }


}
