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

    //DELETE ALL MONTHLY PAYROLLS
     void deleteAllMonthlyPayroll();

    //Pagination
    List<MonthlyPayroll> getByPagination(int offset,int size);

    //Pagination with sorting
    List<MonthlyPayroll> getByPaginationSorting(int offset,int size,String field,String direction);

    //Sorting only by field asc &desc
    List<MonthlyPayroll> getBySorting(String field,String direction);

}