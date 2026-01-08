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
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.MonthlyPayroll;
import com.examly.springapp.service.MonthlyPayrollService;

@RestController
@RequestMapping("/monthlyPayroll")
public class MonthlyPayrollController {

    @Autowired
    private MonthlyPayrollService monthlyPayrollService;

    // CREATE
    @PostMapping
    public ResponseEntity<MonthlyPayroll> saveMonthlyPayroll(@RequestBody MonthlyPayroll monthlyPayroll) {
        MonthlyPayroll saved = monthlyPayrollService.saveMonthlyPayroll(monthlyPayroll);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<MonthlyPayroll>> getAllMonthlyPayroll() {
        List<MonthlyPayroll> list = monthlyPayrollService.getAllMonthlyPayroll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPayroll> getMonthlyPayrollById(@PathVariable("id") int id) {
        return monthlyPayrollService.getMonthlyPayrollById(id)
                .map(mp -> new ResponseEntity<>(mp, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPayroll> updateMonthlyPayroll(
            @PathVariable("id") int id,
            @RequestBody MonthlyPayroll monthlyPayroll) {

        MonthlyPayroll updated = monthlyPayrollService.updateMonthlyPayroll(monthlyPayroll, id);

        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMonthlyPayroll(@PathVariable int id) {
        boolean deleted = monthlyPayrollService.deleteMonthlyPayroll(id);

        if (!deleted) {
            return new ResponseEntity<>("Monthly payroll not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Monthly payroll deleted successfully", HttpStatus.OK);
    }
}
