package com.augmentolabs.rmzcorp.realestate.repositories;

import com.augmentolabs.rmzcorp.realestate.entities.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {
}
