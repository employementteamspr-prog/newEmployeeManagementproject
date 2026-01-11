package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.MonthlyPayroll;

@Service
public interface MonthlyPayrollService {

     // SAVE ONE PAYROLL
    MonthlyPayroll saveMonthlyPayroll(MonthlyPayroll monthlyPayroll);

    // SAVE MULTIPLE PAYROLLS
    List<MonthlyPayroll> saveAllMonthlyPayroll(List<MonthlyPayroll> monthlyPayroll);

    // GET ALL PAYROLLS
    List<MonthlyPayroll> getAllMonthlyPayroll();

    //GET PAYROLL BY ID
    MonthlyPayroll getByMonthlyPayrollId(int monthlyPayrollId);

    //GET PAYROLLS BY NET SALARY
    List<MonthlyPayroll> getBymNetSalary(double mNetSalary);

    //UPDATE PAYROLL BY ID
    MonthlyPayroll updateMonthlyPayrollById(int monthlyPayrollId, MonthlyPayroll monthlyPayroll);

    //DELETE PAYROLL BY ID
    boolean deleteById(int monthlyPayrollId);

}