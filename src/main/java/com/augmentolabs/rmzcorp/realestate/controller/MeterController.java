package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Meter;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.MeterRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class MeterController {

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    MeterRepository meterRepository;

    @GetMapping("/meter/{meterId}")
    public Meter getSpecificMeter(@PathVariable long meterId){
        Optional<Meter> meter = meterRepository.findById(meterId);
        if(!meter.isPresent()){
            throw new IdNotFoundException("Id not found"+ meterId);
        }
        return meter.get();

    }


    @PostMapping("/zoneId/{zoneId}/meter")
    public ResponseEntity<Meter> saveNewMeter(@PathVariable long zoneId, @RequestBody Meter meter) throws Exception{
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if(!zone.isPresent()){
            throw new IdNotFoundException("Id not found: " + zoneId);

        }

        Optional<Meter> newMeter = meterRepository.findById(meter.getId());
        if(!newMeter.isPresent()){
            meter.setZone(zone.get());
            meterRepository.save(meter);
        }
        else {
            throw new RuntimeException("Meter already exists with MeterId: "+ meter.getId());
        }

//        URI url = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{zoneId}")
//                .buildAndExpand(meter.getId())
//                .toUri();

        return ResponseEntity.ok().build();
    }
}
