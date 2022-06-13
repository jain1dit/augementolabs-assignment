package com.augmentolabs.rmzcorp.realestate.controllerTest;

import com.augmentolabs.rmzcorp.realestate.entities.*;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class CityController {
    @Autowired
    CityRepository cityRepository;


    @GetMapping("/city/{id}")
    public City getCity(@PathVariable long id) {
        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()){
            throw new IdNotFoundException("Id Not found: "+ id);
        }
        return city.get();

    }


    @PostMapping("/city")
    public ResponseEntity<City> createCity(@Valid @RequestBody City city) throws Exception {
         Optional<City> newCity = cityRepository.findById(city.getId());
         if(!newCity.isPresent()){
             city.setActive(true);
             cityRepository.save(city);
         }
         else {
             throw new Exception("City already Present with Id: "+ city.getId());
         }

//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(city.getId())
//                .toUri();

        return ResponseEntity.ok().build();
    }

//    @PutMapping("/city/{id}")
//    public ResponseEntity<City> updateCity(@PathVariable long id, @RequestBody City newCity) {
//        // get city by id
//        // set in active and save db
//        // save new city
//
//    }

}
