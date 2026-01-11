package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

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

import com.examly.springapp.model.DailyPayroll;
import com.examly.springapp.service.DailyPayrollService;

@RestController
@RequestMapping("/api/dailypayroll")
public class DailyPayrollController {

    @Autowired
    private DailyPayrollService dailyPayrollService;
    @PostMapping
    public ResponseEntity<DailyPayroll> addDailyPayroll(
            @RequestBody DailyPayroll dailyPayroll) {

        DailyPayroll newPayroll = dailyPayrollService.addDailyPayroll(dailyPayroll);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPayroll);
    }
    @PostMapping("/batch")
    public ResponseEntity<List<DailyPayroll>> addMultipleDailyPayrolls( @RequestBody List<DailyPayroll> dailyPayrolls) {
          List<DailyPayroll> savedPayrolls =dailyPayrollService.addAllDailyPayrolls(dailyPayrolls);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayrolls);
    }
    @GetMapping
    public ResponseEntity<List<DailyPayroll>> getAllDailyPayroll() {
        return ResponseEntity.ok(dailyPayrollService.getAllDailyPayroll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDailyPayrollById(@PathVariable Long id) { 
        Optional<DailyPayroll> payroll = dailyPayrollService.getDailyPayrollById(id); 
        return ResponseEntity.ok(payroll); 
    }
    @GetMapping("/basicSalary/{basicSalary}")
    public ResponseEntity<DailyPayroll> getDailyPayrollByBasicSalary(@PathVariable double basicSalary) {
        Optional<DailyPayroll> payroll =dailyPayrollService.getDailyPayrollByBasicSalary(basicSalary);
      if (payroll.isPresent()) {
        return ResponseEntity.ok(payroll.get());
      }else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }
    @GetMapping("/netSalary/{netSalary}")
    public ResponseEntity<DailyPayroll> getDailyPayrollByNetSalary(@PathVariable double netSalary) {
        Optional<DailyPayroll> payroll=dailyPayrollService.getDailyPayrollByNetSalary(netSalary);
        return payroll.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<DailyPayroll> updateDailyPayroll(@PathVariable Long id,@RequestBody DailyPayroll dailyPayroll) {
        DailyPayroll updatedPayroll=dailyPayrollService.updateDailyPayroll(id, dailyPayroll);
        return ResponseEntity.ok(updatedPayroll);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDailyPayrollById(@PathVariable Long id) {
        boolean deleted=dailyPayrollService.deleteDailyPayrollById(id);
        if(deleted){
            return ResponseEntity.ok("Daily payroll deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Daily payroll not found");
    }
}
