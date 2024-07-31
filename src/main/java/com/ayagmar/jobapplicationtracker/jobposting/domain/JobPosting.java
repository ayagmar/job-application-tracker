package com.ayagmar.jobapplicationtracker.jobposting.domain;

import com.ayagmar.jobapplicationtracker.common.Auditable;
import com.ayagmar.jobapplicationtracker.jobposting.enums.EmploymentStatus;
import com.ayagmar.jobapplicationtracker.jobposting.enums.WorkplaceType;
import com.ayagmar.jobapplicationtracker.location.domain.City;
import com.ayagmar.jobapplicationtracker.location.domain.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class JobPosting extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    private String location;

    private String summary;

    private String description;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonBackReference
    private City city;

    private EmploymentStatus employmentStatus;

    private WorkplaceType workplaceType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobPosting that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}