package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Department;

public interface DepartmentService {

    Department addDepartment(Department department);
    List<Department> addDepartments(List<Department> departments);
    Department getDepartmentByDepartmentName(String departmentName);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department department);
    boolean deleteDepartment(Long id);
}