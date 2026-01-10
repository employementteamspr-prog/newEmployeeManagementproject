package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.JobDescription;
import com.examly.springapp.repository.JobDescriptionRepo;

public class JobDescriptionServiceImpl implements JobDescriptionService {

    private JobDescriptionRepo jobDescriptionRepo;

    public JobDescriptionServiceImpl(JobDescriptionRepo jobDescriptionRepo) {
        this.jobDescriptionRepo = jobDescriptionRepo;
    }

    @Override
    public JobDescription addJobDescription(JobDescription jobDescription) {
        return jobDescriptionRepo.save(jobDescription);
    }

    @Override
    public JobDescription getJobDescriptionById(Long id) {
        return jobDescriptionRepo.findById(id).orElse(null);
    }

    @Override
    public List<JobDescription> getAllJobDescriptions() {
        return jobDescriptionRepo.findAll();
    }

    @Override
    public JobDescription updateJobDescription(Long id, JobDescription updatedJobdescription) {
        if (jobDescriptionRepo.existsById(id)) {
            JobDescription toUpdate = jobDescriptionRepo.findById(id).orElse(null);
            toUpdate.setJobTitle(updatedJobdescription.getJobTitle());
            toUpdate.setResponsibilities(updatedJobdescription.getResponsibilities());
            toUpdate.setDepartment(updatedJobdescription.getDepartment());
            return jobDescriptionRepo.save(toUpdate);
        }
        return null;
    }

    @Override
    public boolean deleteJobDescription(Long id) {
        if (jobDescriptionRepo.existsById(id)) {
            jobDescriptionRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // public List<JobDescription> getJobDescriptionsByDepartment(Long departmentId) {
    //     return jobDescriptionRepo.findByDepartment_DepartmentId(departmentId);
    // }

    @Override
    public List<JobDescription> getJobDescriptionsByDepartment(Long departmentId) {
        return jobDescriptionRepo.findByDepartment_DepartmentId(departmentId);
    }
}