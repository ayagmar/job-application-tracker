package com.ayagmar.jobapplicationtracker.location.repository;

import com.ayagmar.jobapplicationtracker.location.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
}