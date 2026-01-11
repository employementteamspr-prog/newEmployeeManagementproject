package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.examly.springapp.model.Allowance;
import com.examly.springapp.service.AllowanceService;

@RestController
@RequestMapping("/allowance")
public class AllowanceController {

    @Autowired
    private AllowanceService allowanceService;

    //POST SINGLE ALLOWANCE
    @PostMapping
    public ResponseEntity<Allowance> addAllowance(@RequestBody Allowance allowance) {
        try{
            Allowance savedAllowance = allowanceService.addAllowance(allowance);
            return new ResponseEntity<>(savedAllowance, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //POST MULTIPLE ALLOWANCES
    @PostMapping("/multiple")
    public ResponseEntity<List<Allowance>> addAllowances(@RequestBody List<Allowance> allowances) {
        try{
            List<Allowance> savedAllowances = allowanceService.addAllowances(allowances);
            return new ResponseEntity<>(savedAllowances, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //GET ALL ALLOWANCES
    @GetMapping
    public ResponseEntity<List<Allowance>> getAllAllowances(){
        List<Allowance> allowances=allowanceService.getAllAllowances();
        if(allowances.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allowances,HttpStatus.OK);
    }

    //GET ALLOWANCE BY ID
    @GetMapping("/{allowanceId}")
    public ResponseEntity<Allowance> getAllowanceById(@PathVariable int allowanceId){
        Allowance allowance=allowanceService.getAllowanceById(allowanceId);
        if(allowance==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allowance,HttpStatus.OK);
    }

    //GET ALLOWANCES BY AMOUNT
    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<Allowance>> getByAmount(@PathVariable double amount){
        List<Allowance> allowances=allowanceService.getByAmount(amount);
        if(allowances.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allowances,HttpStatus.OK);
    }
    //GET ALLOWANCES BY FREQUENCY
    @GetMapping("/frequency/{frequency}")   
    public ResponseEntity<List<Allowance>> getByFrequency(@PathVariable String frequency){
        List<Allowance> allowances=allowanceService.getByFrequency(frequency);
        if(allowances.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allowances,HttpStatus.OK);
    }

    //UPDATE ALLOWANCE BY ID
    @PutMapping("/{allowanceId}")   
    public ResponseEntity<Allowance> updateAllowanceById(@PathVariable int allowanceId, @RequestBody Allowance allowance){
        Allowance updatedAllowance=allowanceService.updateAllowanceById(allowanceId, allowance);
        if(updatedAllowance==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAllowance,HttpStatus.OK);
    }

    //DELETE ALLOWANCE BY ID
    @DeleteMapping("/{allowanceId}")
    public ResponseEntity<String> deleteAllowanceById(@PathVariable int allowanceId){
        boolean isDeleted=allowanceService.deleteAllowanceById(allowanceId);
        if(isDeleted){
            return new ResponseEntity<>("Allowance deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //DELETE ALL ALLOWANCES
    @DeleteMapping("/all")
        public ResponseEntity<Void> deleteAllAllowances() {
           allowanceService.deleteAllAllowances(); 
           return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }

    //GET BY PAGINATION
    @GetMapping("/page")
     public ResponseEntity<List<Allowance>> getAllowancesByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

            List<Allowance> allowances = allowanceService.getAllowanceByPagination(page, size);
            return new ResponseEntity<>(allowances, HttpStatus.OK);
        }

    
    //Get by sorting 
    @GetMapping("/sort")
    public ResponseEntity<List<Allowance>> getAllowancesBySort(
            @RequestParam(defaultValue = "id") String field,
            @RequestParam(defaultValue = "asc") String direction) {

        List<Allowance> allowances = allowanceService.getBySorting(field, direction);
        return new ResponseEntity<>(allowances, HttpStatus.OK);
    }
    
    //by pagination and sorting
    @GetMapping("/page_sort")
    public ResponseEntity<List<Allowance>> getAllowances(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String field,
        @RequestParam(defaultValue = "asc") String direction) {

        List<Allowance> allowances = allowanceService.getBySortingPagination(page, size, field, direction);
        return new ResponseEntity<>(allowances, HttpStatus.OK);
    }

}





    
