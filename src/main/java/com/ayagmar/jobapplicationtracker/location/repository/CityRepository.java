package com.ayagmar.jobapplicationtracker.location.repository;

import com.ayagmar.jobapplicationtracker.location.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByName(String name);
}