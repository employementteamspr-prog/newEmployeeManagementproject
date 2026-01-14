package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.DailyPayroll;
import com.examly.springapp.service.DailyPayrollService;

@RestController
@RequestMapping("/api/dailypayroll")
public class DailyPayrollController {

    @Autowired
    private DailyPayrollService dailyPayrollService;

    // ===================== EXISTING CRUD ENDPOINTS =====================

    @PostMapping
    public ResponseEntity<DailyPayroll> addDailyPayroll(@RequestBody DailyPayroll dailyPayroll) {
        DailyPayroll newPayroll = dailyPayrollService.addDailyPayroll(dailyPayroll);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPayroll);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<DailyPayroll>> addMultipleDailyPayrolls(@RequestBody List<DailyPayroll> dailyPayrolls) {
        List<DailyPayroll> savedPayrolls = dailyPayrollService.addAllDailyPayrolls(dailyPayrolls);
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
        Optional<DailyPayroll> payroll = dailyPayrollService.getDailyPayrollByBasicSalary(basicSalary);
        return payroll.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/netSalary/{netSalary}")
    public ResponseEntity<DailyPayroll> getDailyPayrollByNetSalary(@PathVariable double netSalary) {
        Optional<DailyPayroll> payroll = dailyPayrollService.getDailyPayrollByNetSalary(netSalary);
        return payroll.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyPayroll> updateDailyPayroll(@PathVariable Long id, @RequestBody DailyPayroll dailyPayroll) {
        DailyPayroll updatedPayroll = dailyPayrollService.updateDailyPayroll(id, dailyPayroll);
        return ResponseEntity.ok(updatedPayroll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDailyPayrollById(@PathVariable Long id) {
        boolean deleted = dailyPayrollService.deleteDailyPayrollById(id);
        if (deleted) {
            return ResponseEntity.ok("Daily payroll deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Daily payroll not found");
    }

    // ===================== NEW PAGINATION & SORTING ENDPOINTS =====================

    // Simple Pagination
    @GetMapping("/page")
    public ResponseEntity<Page<DailyPayroll>> getDailyPayrollsByPagination(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<DailyPayroll> payrolls = dailyPayrollService.getDailyPayrollsByPagination(pageNo, pageSize);
        return ResponseEntity.ok(payrolls);
    }

    // Pagination + Sorting
    @GetMapping("/page/sort")
    public ResponseEntity<Page<DailyPayroll>> getDailyPayrollsByPaginationAndSorting(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "dpayrollId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<DailyPayroll> payrolls = dailyPayrollService.getDailyPayrollsByPaginationAndSorting(pageNo, pageSize, sortBy, direction);
        return ResponseEntity.ok(payrolls);
    }

    // Sorting without Pagination
    @GetMapping("/sort")
    public ResponseEntity<List<DailyPayroll>> sortDailyPayroll(
            @RequestParam(defaultValue = "dpayrollId") String sortBy) {

        List<DailyPayroll> sortedPayrolls;

        switch (sortBy) {
            case "dbasicSalary":
                sortedPayrolls = dailyPayrollService.sortByDbasicSalary();
                break;
            case "ddate":
                sortedPayrolls = dailyPayrollService.sortByDdate();
                break;
            default:
                sortedPayrolls = dailyPayrollService.sortByDpayrollId();
        }

        return ResponseEntity.ok(sortedPayrolls);
    }
}
