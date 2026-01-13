package com.examly.springapp.service;

import java.util.List;



import com.examly.springapp.model.Allowance;
import org.springframework.data.domain.Page;



public interface AllowanceService {

    // POST SINGLE ALLOWANCE
    Allowance addAllowance(Allowance allowance);

    // POST MULTIPLE ALLOWANCES
    List<Allowance> addAllowances(List<Allowance> allowances);

    // GET ALL ALLOWANCES
    List<Allowance> getAllAllowances();

    // GET BY ALLOWANCE ID
    Allowance getAllowanceById(int allowanceId);

    // GET MULTIPLE ALLOWANCES BY AMOUNT
    List<Allowance> getByAmount(double amount);

    // GET MULTIPLE ALLOWANCES BY FREQUENCY LIKE DAILY/MONTHLY/YEARLY
    List<Allowance> getByFrequency(String frequency);

    // UPDATE ALLOWANCE BY ID
    Allowance updateAllowanceById(int allowanceId, Allowance allowance);

    // DELETE ALLOWANCE BY ID 
    boolean deleteAllowanceById(int allowanceId);

    //DELETE ALL ALLOWANCES
    void deleteAllAllowances();

    //Pagination
    Page<Allowance> getAllowanceByPagination(int offset,int size);

    //Pagination with sorting
    Page<Allowance> getBySortingPagination(int offset,int size,String field,String direction);


    //Sorting
    List<Allowance> getBySorting(String field,String direction);

    //Sorting by ID
    public List<Allowance> getBySortingID();

    //Sorting by type
    public List<Allowance> getBySortingType();

    //Sorting by Amount
    public List<Allowance> getBySortingAmount();

    //Sorting by Frequency
    public List<Allowance> getBySortingFrequency();


}
