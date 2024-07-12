package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    List<JobPosting> findByCompanyId(Long companyId);

    List<JobPosting> findByCityId(Long cityId);

    List<JobPosting> findByPositionContainingIgnoreCase(String position);
}