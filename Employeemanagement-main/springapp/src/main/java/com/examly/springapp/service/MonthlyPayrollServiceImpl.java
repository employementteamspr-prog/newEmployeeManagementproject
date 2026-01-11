package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<MonthlyPayroll> getBymNetSalary(double mNetSalary){
        return monthlyPayrollRepo.findBymNetSalary(mNetSalary);
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

    //DELETE ALL MONTHLY PAYROLLS
    @Override
    public void deleteAllMonthlyPayroll() {
        monthlyPayrollRepo.deleteAll();
    }

    //Pagination
    @Override
    public List<MonthlyPayroll> getByPagination(int offset,int size){
        Pageable pageable = PageRequest.of(offset,size);
        return monthlyPayrollRepo.findAll(pageable).getContent();
    }

    //pagination with sorting
    @Override
    public List<MonthlyPayroll> getByPaginationSorting (int offset,int size,String field,String direction){
          Sort sortByField=direction.equalsIgnoreCase("desc")?
            Sort.by(field).descending():
            Sort.by(field).ascending();
        Pageable pageable=PageRequest.of(offset,size,sortByField);
        return monthlyPayrollRepo.findAll(pageable).getContent();
    }

    //Sorting only by field asc &desc
    @Override
    public List<MonthlyPayroll> getBySorting(String field,String direction){    
        Sort sortByField=direction.equalsIgnoreCase("desc")?
        Sort.by(field).descending():
        Sort.by(field).ascending();
        return monthlyPayrollRepo.findAll(sortByField);
    }   
}

    
