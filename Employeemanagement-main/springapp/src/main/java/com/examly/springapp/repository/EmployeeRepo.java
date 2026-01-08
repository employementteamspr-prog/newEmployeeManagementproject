package com.examly.springapp.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
    Employee findByName(String name);

    Employee findByDateOfJoining(LocalDate dateOfJoining);

    Employee findByJobRoleId(int jobRoleId);

    Optional<Employee> findById(Long id);

    void deleteById(Long id);
}
