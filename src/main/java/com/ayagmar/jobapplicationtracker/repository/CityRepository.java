package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}