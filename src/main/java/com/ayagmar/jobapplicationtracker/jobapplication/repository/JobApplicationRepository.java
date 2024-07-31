package com.ayagmar.jobapplicationtracker.jobapplication.repository;

import com.ayagmar.jobapplicationtracker.jobapplication.domain.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.jobapplication.domain.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    Page<JobApplication> findByStatus(ApplicationStatus status,
                                      Pageable pageable);

    List<JobApplication> findByApplicationDateBetween(LocalDateTime start, LocalDateTime end);

}