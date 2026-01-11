package com.examly.springapp.service;

<<<<<<< HEAD
public class EmployeeService {
    
}
=======
import java.time.LocalDate;
import java.util.List;

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
} 
>>>>>>> 8db20400df5488e55e17061a4d64e79d36c54ebe
