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

import com.examly.springapp.model.Department;
import com.examly.springapp.service.DepartmentService;




@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Add Department
    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department addedDepartment = departmentService.addDepartment(department);
        if (addedDepartment != null) {
            return ResponseEntity.ok(addedDepartment);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Add multiple departments
    @PostMapping("/addMultiple")
    public ResponseEntity<List<Department>> addDepartments(@RequestBody List<Department> departments) {
        List<Department> addedDepartments = departmentService.addDepartments(departments);
        if (addedDepartments != null) {
            return ResponseEntity.ok(addedDepartments);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get Department By Id
    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get All Departments
    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments != null) {
            return ResponseEntity.ok(departments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get Department By DepartmentName
    @GetMapping("/getByName/{departmentName}")
    public ResponseEntity<Department> getDepartmentByDepartmentName(@PathVariable String departmentName) {
        Department department = departmentService.getDepartmentByDepartmentName(departmentName);
        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update Department
    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        if (updatedDepartment != null) {
            return ResponseEntity.ok(updatedDepartment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Departments
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (deleted) {
            return ResponseEntity.ok(deleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}