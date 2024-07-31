package com.ayagmar.jobapplicationtracker.document.repository;

import com.ayagmar.jobapplicationtracker.document.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}