package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examly.springapp.model.JobDescription;

public interface JobDescriptionService {

    // Add JobDescription
    public JobDescription addJobDescription(JobDescription jobDescription);

    // Add multiple JobDescriptions
    public List<JobDescription> addJobDescriptions(List<JobDescription> jobDescriptions);


    // Get By Id
    public JobDescription getJobDescriptionById(Long id);

    // Get All JobDescriptions
    public List<JobDescription> getAllJobDescriptions();

    // Update JobDescription
    public JobDescription updateJobDescription(Long id, JobDescription jobDescription);

    // Delete JobDescription
    public boolean deleteJobDescription(Long id);

    // Get Job Description By Department
    public List<JobDescription> getJobDescriptionsByDepartment(Long departmentId);

    Page<JobDescription> getJobDescriptionsWithPagination(Pageable pageable);
}