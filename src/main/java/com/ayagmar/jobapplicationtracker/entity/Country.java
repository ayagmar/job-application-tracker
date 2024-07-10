package com.ayagmar.jobapplicationtracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "countries")
@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 2)
    private String code;

}