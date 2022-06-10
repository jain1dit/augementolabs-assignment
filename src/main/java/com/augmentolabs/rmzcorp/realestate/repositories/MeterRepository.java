package com.augmentolabs.rmzcorp.realestate.repositories;

import com.augmentolabs.rmzcorp.realestate.entities.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
}
