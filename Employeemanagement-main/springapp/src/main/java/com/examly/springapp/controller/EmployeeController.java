package com.examly.springapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee saved=employeeService.saveEmployee(employee);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> saveEmployees(@RequestBody List<Employee> employees){
        List<Employee> savedEmployees=employeeService.saveEmployees(employees);
        return new ResponseEntity<>(savedEmployees,HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees=employeeService.getEmployees();

        if(employees!=null){
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee=employeeService.getEmployeeById(id);

        if(employee!=null){
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/name/{name}")
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable String name){
        Employee employee=employeeService.getEmployeeByName(name);

        if(employee!=null){
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // Get employee by date of joining
    @GetMapping("/employee/date/{date}")
    public ResponseEntity<Employee> getEmployeeByDateOfJoining(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Employee employee = employeeService.getEmployeeByDateOfJoining(date);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Get employee by job role ID
    @GetMapping("/jobrole/{jobRoleId}")
    public ResponseEntity<Employee> getEmployeeByJobRoleId(@PathVariable int jobRoleId) {
        Employee employee = employeeService.getEmployeeByJobRoleId(jobRoleId);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Update employee by ID
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id) {
        Employee updated = employeeService.updateEmployeeById(id);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Delete employee by ID
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long id) {
        boolean deleted = employeeService.deletEmployeeById(id);

        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    // Delete all employees
    @DeleteMapping("/employee")
    public ResponseEntity<Boolean> deleteEmployees() {
        boolean deleted = employeeService.deleteEmployees();

        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }
    // ===============================
    // PAGINATION ONLY
    // ===============================
    @GetMapping("/employees/page")
    public ResponseEntity<Page<Employee>> getEmployeesWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        return new ResponseEntity<>(
                employeeService.getEmployeesWithPagination(page, size),
                HttpStatus.OK
        );
    }

    // ===============================
    // SORTING ONLY
    // ===============================
    @GetMapping("/employees/sort")
    public ResponseEntity<List<Employee>> getEmployeesWithSorting(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return new ResponseEntity<>(
                employeeService.getEmployeesWithSorting(field, direction),
                HttpStatus.OK
        );
    }

    // ===============================
    // PAGINATION + SORTING
    // ===============================
    @GetMapping("/employees/page-sort")
    public ResponseEntity<Page<Employee>> getEmployeesWithPaginationAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return new ResponseEntity<>(
                employeeService.getEmployeesWithPaginationAndSorting(
                        page, size, field, direction),
                HttpStatus.OK
        );
    }

    // ===============================
    // SORT BY SPECIFIC FIELDS
    // ===============================
    @GetMapping("/employees/sort/name")
    public List<Employee> sortByName() {
        return employeeService.sortByName();
    }

    @GetMapping("/employees/sort/date")
    public List<Employee> sortByDate() {
        return employeeService.sortByDateOfJoining();
    }

    @GetMapping("/employees/sort/jobrole")
    public List<Employee> sortByJobRole() {
        return employeeService.sortByJobRoleId();
    }
}

