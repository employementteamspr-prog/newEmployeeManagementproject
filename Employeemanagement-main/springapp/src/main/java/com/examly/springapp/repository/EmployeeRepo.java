package com.examly.springapp.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{
    Employee findByName(String name);

    Employee findByDateOfJoining(LocalDate dateOfJoining);

    Employee findByJobRoleId(int jobRoleId);

    Optional<Employee> findById(Long id);

    void deleteById(Long id);

    // Pagination support
    Page<Employee> findAll(Pageable pageable);
}
