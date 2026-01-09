package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Allowance;

@Service
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
}
