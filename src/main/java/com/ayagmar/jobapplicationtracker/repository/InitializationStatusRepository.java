package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.model.InitializationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InitializationStatusRepository extends JpaRepository<InitializationStatus, String> {
    Optional<InitializationStatus> findByName(String name);
}
