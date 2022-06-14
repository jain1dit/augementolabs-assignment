package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCity() {

        return ResponseEntity.ok(cityService.getAllCities());
    }


    @GetMapping("/city/{cityId}")
    public ResponseEntity<City> getCityById(@PathVariable long cityId) {
        return ResponseEntity.ok(cityService.getCityById(cityId));

    }

    @PostMapping("/city")
    public ResponseEntity<City> saveNewCity(@Valid @RequestBody City city) throws Exception {
        City savedCity = cityService.saveNewCity(city);

        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCity.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }


    @PutMapping("/city/{id}")
    public ResponseEntity<City> updateCity(@PathVariable long id, @RequestBody City newCity) {
        return ResponseEntity.ok(cityService.updateCity(id, newCity));
    }

    @DeleteMapping("/city/{cityId}")
    public ResponseEntity<City> deleteCity(@PathVariable long cityId) throws Exception {
        cityService.deleteCity(cityId);
        return ResponseEntity.noContent().build();
    }


}
