package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.MonthlyPayroll;
import com.examly.springapp.repository.MonthlyPayrollRepo;

@Service
public class MonthlyPayrollServiceImpl implements MonthlyPayrollService{
    @Autowired MonthlyPayrollRepo monthlyPayrollRepo;

    //POST ONE BY ONE
    @Override
    public MonthlyPayroll saveMonthlyPayroll(MonthlyPayroll monthlyPayroll){
        return  monthlyPayrollRepo.save(monthlyPayroll);
    }

    //POST ALL
    @Override
    public List<MonthlyPayroll> saveAllMonthlyPayroll(List<MonthlyPayroll> monthlyPayroll){
        return monthlyPayrollRepo.saveAll(monthlyPayroll);

    }

    //GET ALL
    @Override
    public List<MonthlyPayroll> getAllMonthlyPayroll(){
        return monthlyPayrollRepo.findAll();
    }

    //GET BY MONTHLYPAYROLL ID
    @Override
    public MonthlyPayroll getByMonthlyPayrollId(int monthlyPayrollId){
        return monthlyPayrollRepo.findById(monthlyPayrollId).orElse(null);
    
    }

    //GET BY NET SALARY
    @Override
    public List<MonthlyPayroll> getByNetSalary(double netSalary){
        return monthlyPayrollRepo.findByNetSalary(netSalary);
    }

    //PUT BY MONTHLYPAYROLL ID
    @Override
    public MonthlyPayroll updateMonthlyPayrollById(int monthlyPayrollId,MonthlyPayroll monthlyPayroll){
        MonthlyPayroll existingMonthlyPayroll=monthlyPayrollRepo.findById(monthlyPayrollId).orElse(null);
        if(existingMonthlyPayroll==null){
            return null;
        }
        existingMonthlyPayroll.setmBasicSalary(monthlyPayroll.getmBasicSalary());             
        existingMonthlyPayroll.setmNetSalary(monthlyPayroll.getmNetSalary());
        existingMonthlyPayroll.setmDeductions(monthlyPayroll.getmDeductions());
        existingMonthlyPayroll.setMonth(monthlyPayroll.getMonth());
        return monthlyPayrollRepo.save(existingMonthlyPayroll);
    }

    //DELETE BY MONTHLYPAYROLL ID
    @Override
     public boolean deleteById(int monthlyPayrollId) {
        if (monthlyPayrollRepo.existsById(monthlyPayrollId)) {
            monthlyPayrollRepo.deleteById(monthlyPayrollId);
            return true;
        }
        return false;
    }
}

    
