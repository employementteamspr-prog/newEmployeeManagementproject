package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.MonthlyPayroll;
import com.examly.springapp.model.Salary;
import com.examly.springapp.repository.SalaryRepo;

@Service
public class SalaryService {
    @Autowired 
    private SalaryRepo salaryRepo;

    //Post (save Salary)
    public Salary saveSalary(Salary salary){
        return salaryRepo.save(salary);
    }

    //Get (fetch all Salary)
    public List<Salary> getAllSalary(){
        return salaryRepo.findAll();
    }

    //Get by id (Salary by id)
    public Optional<Salary> getSalaryById(int id){
        return salaryRepo.findById(id);
    }
    //Put (update Salary by id)
    public Salary updateSalary(int id, Salary salary){
        Optional<Salary> existingSalary=salaryRepo.findById(id);
        if(existingSalary.isPresent()){
            Salary s=existingSalary.get();
            s = salary;
            return salaryRepo.save(s);
        }
        return null;
    }
    
        

    }
}
