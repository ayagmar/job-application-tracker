package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserId(Long userId);

    List<JobApplication> findByStatus(ApplicationStatus status);

    List<JobApplication> findByApplicationDateBetween(LocalDateTime start, LocalDateTime end);

    List<JobApplication> findByJobPostingCompanyId(Long companyId);
}