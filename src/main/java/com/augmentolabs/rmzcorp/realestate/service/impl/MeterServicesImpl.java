package com.augmentolabs.rmzcorp.realestate.service.impl;

import com.augmentolabs.rmzcorp.realestate.entities.Meter;
import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import com.augmentolabs.rmzcorp.realestate.exceptions.IdNotFoundException;
import com.augmentolabs.rmzcorp.realestate.repositories.MeterRepository;
import com.augmentolabs.rmzcorp.realestate.repositories.ZoneRepository;
import com.augmentolabs.rmzcorp.realestate.service.MeterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterServicesImpl implements MeterServices {

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    ZoneRepository zoneRepository;

    @Override
    public Meter getSpecificMeter(long meterId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if(!meter.isPresent()){
            throw new IdNotFoundException("Id not found"+ meterId);
        }
        return meter.get();
    }

    @Override
    public Meter saveNewMeter(long zoneId, Meter meter) {
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if(!zone.isPresent()){
            throw new IdNotFoundException("Id not found: " + zoneId);

        }

        meter.setZone(zone.get());
        return meterRepository.save(meter);
    }

    @Override
    public void deleteMeter(long meterId) {
        Optional<Meter> meter = meterRepository.findById(meterId);
        if(!meter.isPresent()){
            throw new IdNotFoundException("Meter Id not found");
        }
        meterRepository.deleteById(meterId);

    }

    @Override
    public Meter updateMeter(long zoneId, long meterId, Meter meter) {
        Optional<Zone> zone = zoneRepository.findById(zoneId);
        if(!zone.isPresent()){
            throw new IdNotFoundException("Zone Id not found: "+ zoneId);
        }
        List<Meter> metersList = zone.get().getMeterType();
        for(Meter meters : metersList ){
            if(meters.getId()==meterId){
                deleteMeter(meterId);
            }
        }

        meter.setId(meterId);
        return saveNewMeter(zoneId, meter);
    }


}
