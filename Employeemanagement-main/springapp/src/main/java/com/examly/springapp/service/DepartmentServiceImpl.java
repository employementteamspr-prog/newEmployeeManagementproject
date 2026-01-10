package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Department;
import com.examly.springapp.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartment) {
        if (departmentRepo.existsById(id)) {
            Department toupdate = departmentRepo.findById(id).orElse(null);
            toupdate.setDepartmentName(updatedDepartment.getDepartmentName());
            toupdate.setDescription(updatedDepartment.getDescription());
            return departmentRepo.save(toupdate);
        }
        return null;
    }

    @Override
    public boolean deleteDepartment(Long id) {
        if (departmentRepo.existsById(id)) {
            departmentRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Department> addDepartments(List<Department> departments) {
        return departmentRepo.saveAll(departments);
    }

    @Override
    public Department getDepartmentByDepartmentName(String departmentName) {
        return departmentRepo.findByDepartmentName(departmentName);
    }
}