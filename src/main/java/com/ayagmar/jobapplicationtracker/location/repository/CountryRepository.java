package com.ayagmar.jobapplicationtracker.location.repository;

import com.ayagmar.jobapplicationtracker.location.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    boolean existsByCode(String code);
    Optional<Country> findByCode(String code);
}
