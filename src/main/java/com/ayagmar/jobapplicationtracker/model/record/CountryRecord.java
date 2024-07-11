package com.ayagmar.jobapplicationtracker.model.record;


import java.io.Serializable;
import java.util.List;


public record CountryRecord(String name, String code, List<String> cities) implements Serializable {
}