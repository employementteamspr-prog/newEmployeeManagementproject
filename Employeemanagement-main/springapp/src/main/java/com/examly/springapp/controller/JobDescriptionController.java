package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<JobDescription> addJobDescription(@RequestBody JobDescription jobDescription) {
        JobDescription addedJobDescription = jobDescriptionService.addJobDescription(jobDescription);
        if (addedJobDescription != null) {
            return ResponseEntity.ok(addedJobDescription);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // Add all JobDescriptions
    @PostMapping("/addMultiple")
    public ResponseEntity<List<JobDescription>> addJobDescriptions(@RequestBody List<JobDescription> jobDescriptions) {
        List<JobDescription> addedJobDescriptions = jobDescriptionService.addJobDescriptions(jobDescriptions);
        if (addedJobDescriptions != null) {
            return ResponseEntity.ok(addedJobDescriptions);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // Get By Id
    @GetMapping("/get/{id}")
    public ResponseEntity<JobDescription> getJobDescriptionById(@PathVariable Long id) {
        JobDescription jobDescription = jobDescriptionService.getJobDescriptionById(id);
        if (jobDescription != null) {
            return ResponseEntity.ok(jobDescription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Get All JobDescriptions
    @GetMapping("/getAll")
    public ResponseEntity<List<JobDescription>> getAllJobDescriptions() {
        List<JobDescription> jobDescriptions = jobDescriptionService.getAllJobDescriptions();
        if (jobDescriptions != null) {
            return ResponseEntity.ok(jobDescriptions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Get Job Description By Department
    @GetMapping("/getByDepartment/{departmentId}")
    public ResponseEntity<List<JobDescription>> getJobDescriptionsByDepartment(@PathVariable Long departmentId) {
        List<JobDescription> jobDescriptions = jobDescriptionService.getJobDescriptionsByDepartment(departmentId);
        if (jobDescriptions != null) {
            return ResponseEntity.ok(jobDescriptions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Update JobDescription
    @PutMapping("/update/{id}")
    public ResponseEntity<JobDescription> updateJobDescription(@PathVariable Long id, @RequestBody JobDescription updatedJobDescription) {
        JobDescription jobDescription = jobDescriptionService.updateJobDescription(id, updatedJobDescription);
        if (jobDescription != null) {
            return ResponseEntity.ok(jobDescription);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete JobDescription
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteJobDescription(@PathVariable Long id) {
        boolean deleted = jobDescriptionService.deleteJobDescription(id);
        if (deleted) {
            return ResponseEntity.ok(deleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}