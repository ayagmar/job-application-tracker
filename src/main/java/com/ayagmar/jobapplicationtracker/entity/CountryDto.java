package com.ayagmar.jobapplicationtracker.entity;


import java.io.Serializable;
import java.util.List;


public record CountryDto(String name, String code, List<String> cities) implements Serializable {
}