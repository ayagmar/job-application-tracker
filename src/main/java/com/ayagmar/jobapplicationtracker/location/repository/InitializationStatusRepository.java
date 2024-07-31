package com.ayagmar.jobapplicationtracker.location.repository;

import com.ayagmar.jobapplicationtracker.location.domain.InitializationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InitializationStatusRepository extends JpaRepository<InitializationStatus, String> {
    Optional<InitializationStatus> findByName(String name);
}
