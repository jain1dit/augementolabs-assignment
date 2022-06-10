package com.augmentolabs.rmzcorp.realestate.repositories;

import com.augmentolabs.rmzcorp.realestate.entities.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Buildings, Long> {
}
