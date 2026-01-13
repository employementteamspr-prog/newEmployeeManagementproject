package com.examly.springapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.MonthlyPayroll;
import com.examly.springapp.service.MonthlyPayrollService;

@RestController
@RequestMapping("/api")
public class MonthlyPayrollController {
    @Autowired
    private MonthlyPayrollService monthlyPayrollService;
    //POST ONE PAYROLL
    @PostMapping("/payroll")
    public ResponseEntity<MonthlyPayroll> generateMonthlyPayroll(@RequestBody MonthlyPayroll monthlyPayroll) {
        try{
            MonthlyPayroll saved=monthlyPayrollService.saveMonthlyPayroll(monthlyPayroll);
            return new ResponseEntity<>(saved,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //POST ALL PAYROLLS
    @PostMapping("/payrolls")
    public ResponseEntity<List<MonthlyPayroll>> generateAllMonthlyPayroll(@RequestBody List<MonthlyPayroll> monthlyPayroll) {
        try{
            List<MonthlyPayroll> savedList=monthlyPayrollService.saveAllMonthlyPayroll(monthlyPayroll);
            return new ResponseEntity<>(savedList,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET ALL PAYROLLS
    @GetMapping("/payrolls")
    public ResponseEntity<List<MonthlyPayroll>> getAllMonthlyPayroll(){
        List<MonthlyPayroll> monthlyPayrolls=monthlyPayrollService.getAllMonthlyPayroll();
        if(monthlyPayrolls.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monthlyPayrolls,HttpStatus.OK);
    }

    //GET PAYROLL BY MONTHLYPAYROLL ID
    @GetMapping("/payroll/{monthlyPayrollId}")
    public ResponseEntity<MonthlyPayroll> getMonthlyPayrollById(@PathVariable int monthlyPayrollId){
        MonthlyPayroll monthlyPayroll=monthlyPayrollService.getByMonthlyPayrollId(monthlyPayrollId);
        if(monthlyPayroll==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monthlyPayroll,HttpStatus.OK);
    }

    //GET PAYROLLS BY NET SALARY
    @GetMapping("/mNetSalary/{mNetSalary}")
    public ResponseEntity<List<MonthlyPayroll>> getMonthlyPayrollBymNetSalary(@PathVariable double mNetSalary){
        List<MonthlyPayroll> monthlyPayrolls=monthlyPayrollService.getBymNetSalary(mNetSalary);
        if(monthlyPayrolls.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(monthlyPayrolls,HttpStatus.OK);
    }

    //UPDATE PAYROLL BY MONTHLYPAYROLL ID
    @PutMapping("/payroll/{monthlyPayrollId}")
    public ResponseEntity<MonthlyPayroll> updateMonthlyPayrollById(@PathVariable int monthlyPayrollId,@RequestBody MonthlyPayroll monthlyPayroll){
        MonthlyPayroll updatedMonthlyPayroll=monthlyPayrollService.updateMonthlyPayrollById(monthlyPayrollId,monthlyPayroll);
        if(updatedMonthlyPayroll==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMonthlyPayroll,HttpStatus.OK);
    }

    //DELETE PAYROLL BY MONTHLYPAYROLL ID
    @DeleteMapping("payroll/{monthlyPayrollId}")
    public ResponseEntity<String> deleteMonthlyPayrollById(@PathVariable int monthlyPayrollId){
        boolean Deleted=monthlyPayrollService.deleteById(monthlyPayrollId);
        if(Deleted){
            return new ResponseEntity<>("Monthly Payroll deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Monthly Payroll not found",HttpStatus.NOT_FOUND);
    }

    //Delete All 
    @DeleteMapping("/payrolls")
    public ResponseEntity<Void> deleteAllMonthlyPayroll(){
        monthlyPayrollService.deleteAllMonthlyPayroll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //pagination
    @GetMapping("/payroll/page")
    public ResponseEntity<Page<MonthlyPayroll>> getByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

            Page<MonthlyPayroll> payrolls = monthlyPayrollService.getByPagination(page, size);
            return new ResponseEntity<>(payrolls, HttpStatus.OK);
        }
        //sorting
        @GetMapping("/payroll/sort")
        public ResponseEntity<List<MonthlyPayroll>> getBySorting(
            @RequestParam(defaultValue = "id") String field,
            @RequestParam(defaultValue = "asc") String direction) {

            List<MonthlyPayroll> payrolls = monthlyPayrollService.getBySorting(field, direction);
            return new ResponseEntity<>(payrolls, HttpStatus.OK);
        }
        //pagination and sorting
         @GetMapping("/payroll/page-sort")
        public ResponseEntity<Page<MonthlyPayroll>> getByPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String field,
            @RequestParam(defaultValue = "asc") String direction) {

            Page<MonthlyPayroll> payrolls = monthlyPayrollService.getByPaginationSorting(page, size, field, direction);
            return new ResponseEntity<>(payrolls, HttpStatus.OK);
        }
    
        //Sorting by ID
        @GetMapping("/payroll/sort/id")
        public ResponseEntity<List<MonthlyPayroll>> getBySortingID(){
            List<MonthlyPayroll> payrolls=monthlyPayrollService.getBySortingID();
            return new ResponseEntity<>(payrolls,HttpStatus.OK);
        }
        //Sorting by Basic Salary
        @GetMapping("/payroll/sort/basicSalary")
        public ResponseEntity<List<MonthlyPayroll>> getBySortingBasicSalary(){
            List<MonthlyPayroll> payrolls=monthlyPayrollService.getBySortingBasicSalary();
            return new ResponseEntity<>(payrolls,HttpStatus.OK);
        }
        //Sorting by Net Salary
        @GetMapping("/payroll/sort/netSalary")
        public ResponseEntity<List<MonthlyPayroll>> getBySortingNetSalary(){
            List<MonthlyPayroll> payrolls=monthlyPayrollService.getBySortingNetSalary();
            return new ResponseEntity<>(payrolls,HttpStatus.OK);
        }
        //Sorting by Deductions
        @GetMapping("/payroll/sort/deductions")
        public ResponseEntity<List<MonthlyPayroll>> getBySortingDeductions(){
            List<MonthlyPayroll> payrolls=monthlyPayrollService.getBySortingDeductions();
            return new ResponseEntity<>(payrolls,HttpStatus.OK);
        }
        //Sorting by Month
        @GetMapping("/payroll/sort/month")
        public ResponseEntity<List<MonthlyPayroll>> getBySortingMonth(){
            List<MonthlyPayroll> payrolls=monthlyPayrollService.getBySortingMonth();
            return new ResponseEntity<>(payrolls,HttpStatus.OK);
        }
        


}