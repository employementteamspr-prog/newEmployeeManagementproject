package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    // Add multiple departments
    @PostMapping("/addMultiple")
    public List<Department> addDepartments(@RequestBody List<Department> departments) {
        return departmentService.addDepartments(departments);
    }

    // Get Department By Id
    @GetMapping("/get/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    // Get All Departments
    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Get Department By DepartmentName
    @GetMapping("/getByName/{departmentName}")
    public Department getDepartmentByDepartmentName(@PathVariable String departmentName) {
        return departmentService.getDepartmentByDepartmentName(departmentName);
    }

    // Update Department
    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    // Delete Departments
    @DeleteMapping("/delete/{id}")
    public boolean deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}