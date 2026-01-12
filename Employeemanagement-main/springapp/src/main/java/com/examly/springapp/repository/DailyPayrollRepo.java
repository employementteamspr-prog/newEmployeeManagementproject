package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.DailyPayroll;


@Repository
public interface DailyPayrollRepo extends JpaRepository<DailyPayroll,Long>{
    Optional<DailyPayroll> findById(Long id);
    Optional<DailyPayroll> findByDbasicSalary(double dbasicSalary);
    Optional<DailyPayroll> findByDnetSalary(double dnetSalary);
}