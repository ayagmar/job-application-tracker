package com.ayagmar.jobapplicationtracker.repository;

import com.ayagmar.jobapplicationtracker.model.InitializationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InitializationStatusRepository extends JpaRepository<InitializationStatus, String> {
    Optional<InitializationStatus> findByName(String name);
}
