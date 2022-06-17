package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.CityRepository;
import com.augmentolabs.rmzcorp.realestate.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

  @Autowired CityRepository cityRepository;

  @Override
  public List<City> getAllCities() {
    return cityRepository.findAll();
  }

  @Override
  public City getCityById(long cityId) throws RuntimeException {
    Optional<City> city = cityRepository.findById(cityId);
    if (!city.isPresent()) {
      throw new IdNotFoundException("City Id Not found: " + cityId);
    }

    return city.get();
  }

  @Override
  public City saveNewCity(City city) {
    return cityRepository.save(city);
  }

  @Override
  public void deleteCity(long cityId) {
    Optional<City> city = cityRepository.findById(cityId);
    if (!city.isPresent()) {
      throw new IdNotFoundException("City ID not found " + cityId);
    }

    cityRepository.deleteById(cityId);
  }

  @Override
  public City updateCity(long id, City newCity) {
    Optional<City> city = cityRepository.findById(id);
    if (city.isPresent()) {
      deleteCity(id);
    } else {
      throw new IdNotFoundException("CityId Not Found ");
    }
    newCity.setId(id);
    return saveNewCity(newCity);
  }
}
