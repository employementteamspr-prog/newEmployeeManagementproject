package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examly.springapp.model.Department;

public interface DepartmentService {

    Department addDepartment(Department department);
    List<Department> addDepartments(List<Department> departments);
    Department getDepartmentByDepartmentName(String departmentName);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department department);
    boolean deleteDepartment(Long id);
    Department getDepartmentByEmployee(Long employeeId);
    Page<Department> getDepartmentsWithPagination(Pageable pageable);
}