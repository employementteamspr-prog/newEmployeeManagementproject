package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Allowance;

@Repository
public interface AllowanceRepo extends JpaRepository<Allowance,Integer>{
    
    List<Allowance> findByFrequency(String frequency);
    List<Allowance> findByAmount(double amount);
    
    //Pagination
    Page<Allowance> getAllowanceByPagination(int offset,int size);

    //Pagination with sorting
    Page<Allowance> getBySortingPagination(int offset,int size,String field,String direction);
    //Sorting by ID
    public List<Allowance> getBySortingID(int allowanceId);

    //Sorting by type
    public List<Allowance> getBySortingType(String type);

    //Sorting by Amount
    public List<Allowance> getBySortingAmount(double amount);

    //Sorting by Frequency
    public List<Allowance> getBySortingFrequency(String frequency);
    
}