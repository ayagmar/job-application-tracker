package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.model.Company;
import com.ayagmar.jobapplicationtracker.model.JobApplication;
import com.ayagmar.jobapplicationtracker.model.JobPosting;
import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationRequestRecord;
import com.ayagmar.jobapplicationtracker.repository.CompanyRepository;
import com.ayagmar.jobapplicationtracker.repository.CountryRepository;
import com.ayagmar.jobapplicationtracker.repository.JobApplicationRepository;
import com.ayagmar.jobapplicationtracker.repository.JobPostingRepository;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;
    private final CountryRepository countryRepository;
    private final CompanyRepository companyRepository;
    private final UserService userService;
    public void createJobListing(JobApplicationRequestRecord record){
        JobApplication jobApplication = JobApplication.builder()
                .company(findCompanyByName(record.companyName()))
                .jobPosting(record.jobPostingRecord().toJobPosting())
                .notes(record.notes())
                .status(ApplicationStatus.APPLIED)
                .lastStatusChangeDate(LocalDate.now())
                .applicationDate(LocalDateTime.now())
                .user(findUserById(record.userId()))
                .build();
        jobApplicationRepository.save(jobApplication);
    }

   @PostConstruct
   private void init(){
        JobPosting jobPosting = JobPosting.builder()
                .url("https://www.cnexia.com/job/P-100192/Senior-Backend-Developer-Java?utm_campaign=google_jobs_apply&utm_source=google_jobs_apply&utm_medium=organic")
                .position("Senior Backend Developer")
                .build();

        Company company = Company.builder()
                .website("https://www.cnexia.com/")
                .industry("IT services")
                .name("Cnexia")
                .country(countryRepository.findByCode("MA").orElse(null))
                .build();
        companyRepository.save(company);
        jobPostingRepository.save(jobPosting);
        JobApplicationRequestRecord job = new JobApplicationRequestRecord(1L,"Cnexia",
                jobPosting.toRecord(),"test app");
        createJobListing(job);
    }

    private Company findCompanyByName(String name){
        return companyRepository.findCompanyByName(name).orElse(null);
    }

    private User findUserById(Long id){
        return userRepository.getReferenceById(id);
    }
}
