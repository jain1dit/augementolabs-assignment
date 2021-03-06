package com.augmentolabs.rmzcorp.realestate.controller;

import com.augmentolabs.rmzcorp.realestate.entities.Meter;
import com.augmentolabs.rmzcorp.realestate.service.MeterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class MeterController {

  @Autowired MeterServices meterServices;

  @GetMapping("/meter/{meterId}")
  public ResponseEntity<Meter> getSpecificMeter(@PathVariable long meterId) {
    return ResponseEntity.ok(meterServices.getSpecificMeter(meterId));
  }

  @PostMapping("/zone/{zoneId}/meter")
  public ResponseEntity<Meter> saveNewMeter(@PathVariable long zoneId, @RequestBody Meter meter)
      throws Exception {
    Meter savedMeter = meterServices.saveNewMeter(zoneId, meter);

    URI url =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/zone/{zoneId}/meter/" + savedMeter.getId())
            .buildAndExpand(savedMeter.getId())
            .toUri();
    return ResponseEntity.created(url).build();
  }

  @DeleteMapping("/meter/{meterId}")
  public ResponseEntity<Meter> deleteMeter(@PathVariable long meterId) {
    meterServices.deleteMeter(meterId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/zone/{zoneId}/meter/{meterId}")
  public ResponseEntity<Meter> updateMeter(
      @PathVariable long zoneId, @PathVariable long meterId, @RequestBody Meter meter) {
    return ResponseEntity.ok(meterServices.updateMeter(zoneId, meterId, meter));
  }
}
