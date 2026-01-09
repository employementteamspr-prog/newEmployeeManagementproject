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

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @GetMapping("/get/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/getAll")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}