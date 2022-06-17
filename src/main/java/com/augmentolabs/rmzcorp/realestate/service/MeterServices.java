package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.Meter;

public interface MeterServices {
  Meter getSpecificMeter(long meterId);

  Meter saveNewMeter(long zoneId, Meter meter);

  void deleteMeter(long meterId);

  Meter updateMeter(long zoneId, long meterId, Meter meter);
}
