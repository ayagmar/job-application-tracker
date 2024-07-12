package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.model.City;
import com.ayagmar.jobapplicationtracker.model.record.CityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByName(String name);

    @Query("SELECT new com.ayagmar.jobapplicationtracker.model.record.CityRecord(c.id, c.name, c.country.name) FROM City c WHERE c.id = :id")
    Optional<CityRecord> findCityDTOById(@Param("id") Long id);
}