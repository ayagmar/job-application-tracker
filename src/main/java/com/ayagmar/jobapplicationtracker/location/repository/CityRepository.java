package com.ayagmar.jobapplicationtracker.location.repository;

import com.ayagmar.jobapplicationtracker.location.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByName(String name);

    @Query("SELECT c FROM City c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :cityName, '%')) OR LOWER(:cityName) LIKE LOWER(CONCAT('%', c.name, '%'))")
    Optional<City> findByCityNameContainingIgnoreCase(@Param("cityName") String cityName);
}