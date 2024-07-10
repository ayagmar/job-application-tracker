package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long> {
    boolean existsByCode(String code);
    Optional<Country> findByCode(String code);
}
