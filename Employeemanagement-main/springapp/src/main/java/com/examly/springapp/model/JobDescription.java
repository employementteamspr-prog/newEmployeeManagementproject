package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JobDescription{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long jobRoleId;
    private String jobTitle;
    private String responsibilities;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    public JobDescription() {
    }

    public JobDescription(Department department, String jobTitle, String responsibilities) {
        this.department = department;
        this.jobTitle = jobTitle;
        this.responsibilities = responsibilities;
    }

    public Long getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(Long jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}