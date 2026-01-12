package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.JobDescription;
import com.examly.springapp.service.JobDescriptionService;
@RestController
@RequestMapping("/jobdescription")
public class JobDescriptionController {

    @Autowired
    private JobDescriptionService jobDescriptionService;

    // Add JobDescription
    @PostMapping("/add")
    public JobDescription addJobDescription(@RequestBody JobDescription jobDescription) {
        return jobDescriptionService.addJobDescription(jobDescription);
    }

    // Get By Id
    @GetMapping("/get/{id}")
    public JobDescription getJobDescriptionById(@PathVariable Long id) {
        return jobDescriptionService.getJobDescriptionById(id);
    }

    // Get All JobDescriptions
    @GetMapping("/getAll")
    public List<JobDescription> getAllJobDescriptions() {
        return jobDescriptionService.getAllJobDescriptions();
    }

    // Get Job Description By Department
    @GetMapping("/getByDepartment/{departmentId}")
    public List<JobDescription> getJobDescriptionsByDepartment(@PathVariable Long departmentId) {
        return jobDescriptionService.getJobDescriptionsByDepartment(departmentId);
    }

    // Update JobDescription
    @PutMapping("/update/{id}")
    public JobDescription updateJobDescription(@PathVariable Long id, @RequestBody JobDescription updatedJobDescription) {
        return jobDescriptionService.updateJobDescription(id, updatedJobDescription);
    }

    // Delete JobDescription
    @DeleteMapping("/delete/{id}")
    public boolean deleteJobDescription(@PathVariable Long id) {
        return jobDescriptionService.deleteJobDescription(id);
    }
}