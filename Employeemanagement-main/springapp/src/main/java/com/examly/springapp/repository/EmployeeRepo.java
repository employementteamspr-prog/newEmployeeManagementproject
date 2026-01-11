package com.examly.springapp.repository;

<<<<<<< HEAD
public class EmployeeRepo {
    
}
=======
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{
    Employee findByName(String name);

    Employee findByDateOfJoining(LocalDate dateOfJoining);

    Employee findByJobRoleId(int jobRoleId);

    Optional<Employee> findById(Long id);

    void deleteById(Long id);
}
>>>>>>> 8db20400df5488e55e17061a4d64e79d36c54ebe
