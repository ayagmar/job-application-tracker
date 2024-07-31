package com.ayagmar.jobapplicationtracker.location.domain;

import com.ayagmar.jobapplicationtracker.common.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "cities")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonBackReference
    private Country country;

//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private Set<JobPosting> jobPostings = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;
        return Objects.equals(getId(), city.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}