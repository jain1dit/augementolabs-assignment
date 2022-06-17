package com.augmentolabs.rmzcorp.realestate.entities;

public enum MeterType {
  ELECTRICITY_METER("Electricity Meter"),
  WATER_METER("Water Meter");

  private final String meterName;

  MeterType(String meterName) {
    this.meterName = meterName;
  }

  public static String getName() {
    return ELECTRICITY_METER.getClass().getName();
  }
}
