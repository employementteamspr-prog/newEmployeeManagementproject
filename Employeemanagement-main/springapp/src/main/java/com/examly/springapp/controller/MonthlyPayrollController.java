package com.examly.springapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.MonthlyPayroll;
import com.examly.springapp.service.MonthlyPayrollService;

@RestController
@RequestMapping("/monthlyPayroll")
public class MonthlyPayrollController {
    @Autowired
    private MonthlyPayrollService monthlyPayrollService;
    //POST ONE PAYROLL
    @PostMapping
    public ResponseEntity<MonthlyPayroll> generateMonthlyPayroll(@RequestBody MonthlyPayroll monthlyPayroll) {
        try{
            MonthlyPayroll saved=monthlyPayrollService.saveMonthlyPayroll(monthlyPayroll);
            return new ResponseEntity<>(saved,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST ALL PAYROLLS
    @PostMapping
    public ResponseEntity<List<MonthlyPayroll>> generateAllMonthlyPayroll(@RequestBody List<MonthlyPayroll> monthlyPayroll) {
        try{
            List<MonthlyPayroll> savedList=monthlyPayrollService.saveAllMonthlyPayroll(monthlyPayroll);
            return new ResponseEntity<>(savedList,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET ALL PAYROLLS
    @GetMapping
    public ResponseEntity<List<MonthlyPayroll>> getAllMonthlyPayroll(){
        List<MonthlyPayroll> monthlyPayrolls=monthlyPayrollService.getAllMonthlyPayroll();
        if(monthlyPayrolls.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monthlyPayrolls,HttpStatus.OK);
    }

    //GET PAYROLL BY MONTHLYPAYROLL ID
    @GetMapping("/{monthlyPayrollId}")
    public ResponseEntity<MonthlyPayroll> getMonthlyPayrollById(@PathVariable int monthlyPayrollId){
        MonthlyPayroll monthlyPayroll=monthlyPayrollService.getByMonthlyPayrollId(monthlyPayrollId);
        if(monthlyPayroll==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monthlyPayroll,HttpStatus.OK);
    }

    //GET PAYROLLS BY NET SALARY
    @GetMapping("/netSalary/{netSalary}")
    public ResponseEntity<List<MonthlyPayroll>> getMonthlyPayrollByNetSalary(@PathVariable double netSalary){
        List<MonthlyPayroll> monthlyPayrolls=monthlyPayrollService.getByNetSalary(netSalary);
        if(monthlyPayrolls.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monthlyPayrolls,HttpStatus.OK);
    }

    //UPDATE PAYROLL BY MONTHLYPAYROLL ID
    @PutMapping("/{monthlyPayrollId}")
    public ResponseEntity<MonthlyPayroll> updateMonthlyPayrollById(@PathVariable int monthlyPayrollId,@RequestBody MonthlyPayroll monthlyPayroll){
        MonthlyPayroll updatedMonthlyPayroll=monthlyPayrollService.updateMonthlyPayrollById(monthlyPayrollId,monthlyPayroll);
        if(updatedMonthlyPayroll==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMonthlyPayroll,HttpStatus.OK);
    }

    //DELETE PAYROLL BY MONTHLYPAYROLL ID
    @GetMapping("/{monthlyPayrollId}")
    public ResponseEntity<String> deleteMonthlyPayrollById(@PathVariable int monthlyPayrollId){
        boolean Deleted=monthlyPayrollService.deleteById(monthlyPayrollId);
        if(Deleted){
            return new ResponseEntity<>("Monthly Payroll deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Monthly Payroll not found",HttpStatus.NOT_FOUND);
    }
}