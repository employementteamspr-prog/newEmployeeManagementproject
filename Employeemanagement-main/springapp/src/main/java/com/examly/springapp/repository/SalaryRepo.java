package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Salary;
@Repository
public interface SalaryRepo extends JpaRepository<Salary, Integer>{

    List<Salary> findByMonth( int month);
}