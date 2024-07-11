package com.ayagmar.jobapplicationtracker.model;

import com.ayagmar.jobapplicationtracker.model.record.JobPostingRecord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    private String url;

    public JobPostingRecord toRecord() {
        return new JobPostingRecord(id, position, url);
    }
}