package com.ayagmar.jobapplicationtracker.model.record;


import java.util.List;


public record CountryRecord(Long id, String name, String code, List<String> cities) {
}
