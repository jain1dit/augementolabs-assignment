package com.augmentolabs.rmzcorp.realestate.repositories;

import com.augmentolabs.rmzcorp.realestate.entities.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
