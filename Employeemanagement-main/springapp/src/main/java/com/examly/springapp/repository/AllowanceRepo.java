package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Allowance;

@Repository
public interface AllowanceRepo extends JpaRepository<Allowance,Integer>{
    
    List<Allowance> findByFrequency(String frequency);
    List<Allowance> findByAmount(double amount);
    
}