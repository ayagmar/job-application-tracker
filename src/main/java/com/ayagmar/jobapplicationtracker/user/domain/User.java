package com.ayagmar.jobapplicationtracker.user.domain;

import com.ayagmar.jobapplicationtracker.common.Auditable;
import com.ayagmar.jobapplicationtracker.document.domain.DocumentType;
import com.ayagmar.jobapplicationtracker.jobapplication.domain.JobApplication;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JobApplication> jobApplications = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_documents",
            joinColumns = @JoinColumn(name = "user_id"))
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