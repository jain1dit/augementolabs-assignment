package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.response.ZoneResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ReportControllerTest {

    @Mock
    ZoneRepository zoneRepository;

    @InjectMocks
    private ReportController reportController;

    @Test
    public void whenZoneSuccess() throws Exception {
        when(zoneRepository.findById(anyLong())).thenReturn(ZoneResponse.getZones());
        assertEquals(ZoneResponse.getZones().get(), reportController.retrieveMeterByZoneId(1L));

    }
}
