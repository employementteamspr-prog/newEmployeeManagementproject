package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepo;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        return employeeRepo.saveAll(employees);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Employee employee = employeeRepo.findByName(name);

        if (employee != null) {
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public Employee getEmployeeByDateOfJoining(LocalDate localDate) {
        Employee employee = employeeRepo.findByDateOfJoining(localDate);

        if (employee != null) {
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public Employee getEmployeeByJobRoleId(int jobRoleId) {
        Employee employee = employeeRepo.findByJobRoleId(jobRoleId);

        if (employee != null) {
            return employee;
        } else {
            return null;
        }
    }

    @Override
public Employee updateEmployeeById(Long id) {

    if (id != null) {
        Employee employee = employeeRepo.findById(id).orElse(null);

        if (employee != null) {
            return employeeRepo.save(employee);
        } else {
            return null;
        }
    } else {
        return null;
    }
}

@Override
public boolean deletEmployeeById(Long id) {

    if (id != null) {
        Employee employee = employeeRepo.findById(id).orElse(null);

        if (employee != null) {
            employeeRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}

@Override
public boolean deleteEmployees() {

    List<Employee> employees = employeeRepo.findAll();

    if (employees != null && !employees.isEmpty()) {
        employeeRepo.deleteAll();
        return true;
    } else {
        return false;
    }
}

}