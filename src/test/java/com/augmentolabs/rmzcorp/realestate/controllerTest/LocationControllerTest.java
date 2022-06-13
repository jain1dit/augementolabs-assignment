package com.augmentolabs.rmzcorp.realestate.controllerTest;

import com.augmentolabs.rmzcorp.realestate.repositories.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LocationControllerTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationController locationController;

    @Test
    public void whenGetLocationSuccess(){

    }

}
