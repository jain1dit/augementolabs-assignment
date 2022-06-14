package com.augmentolabs.rmzcorp.realestate.service;

import com.augmentolabs.rmzcorp.realestate.entities.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    City getCityById(long cityId);

    City saveNewCity(City city);

    void deleteCity(long cityId);

    City updateCity(long id, City newCity);
}
