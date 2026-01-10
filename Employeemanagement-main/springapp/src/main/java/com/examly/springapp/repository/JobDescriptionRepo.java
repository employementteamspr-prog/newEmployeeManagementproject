package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.JobDescription; 



public interface JobDescriptionRepo extends JpaRepository<JobDescription, Long> {

    public List<JobDescription> findByDepartment_DepartmentId(Long departmentId);

    
}