package com.ayagmar.jobapplicationtracker.jobapplication.domain;

import com.ayagmar.jobapplicationtracker.common.Auditable;
import com.ayagmar.jobapplicationtracker.document.domain.DocumentType;
import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.user.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplication extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting;

    @Column(nullable = false)
    private LocalDateTime applicationDate;

    private LocalDate lastStatusChangeDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    private String notes;

    @ElementCollection
    @CollectionTable(name = "job_application_documents",
            joinColumns = @JoinColumn(name = "job_application_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "document_type")
    @Column(name = "document_id")
    private Map<DocumentType, Long> documents = new EnumMap<>(DocumentType.class);

    public void addDocument(DocumentType type, Long documentId) {
        if (documents == null) {
            documents = new EnumMap<>(DocumentType.class);
        }
        this.documents.put(type, documentId);
    }

    public void removeDocument(DocumentType type) {
        this.documents.remove(type);
    }

}