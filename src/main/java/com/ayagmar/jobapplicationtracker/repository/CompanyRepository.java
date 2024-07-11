package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}