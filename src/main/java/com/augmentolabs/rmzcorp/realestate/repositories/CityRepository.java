package com.augmentolabs.rmzcorp.realestate.repositories;

import com.augmentolabs.rmzcorp.realestate.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

//    @Override
//    default Optional<City> findById(Long aLong) {
//        List<City> allCities = findAll();
//        return allCities.stream().filter(City::isActive).findFirst();
//    }
}
