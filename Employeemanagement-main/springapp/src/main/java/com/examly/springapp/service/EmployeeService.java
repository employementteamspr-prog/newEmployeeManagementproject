package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    List<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Employee getEmployeeByName(String name);

    Employee getEmployeeByDateOfJoining(LocalDate loacalDate);

    Employee getEmployeeByJobRoleId(int jobRoleId);

    Employee updateEmployeeById(Long id);

    boolean deletEmployeeById(Long id);

    boolean deleteEmployees();

    // Pagination
    Page<Employee> getEmployeesWithPagination(int page, int size);

    // Sorting
    List<Employee> getEmployeesWithSorting(String field, String direction);

    // Pagination + Sorting
    Page<Employee> getEmployeesWithPaginationAndSorting(
            int page, int size, String field, String direction);
            
     // Sorting by fixed fields
    List<Employee> sortByName();
    List<Employee> sortByDateOfJoining();
    List<Employee> sortByJobRoleId();
} 
