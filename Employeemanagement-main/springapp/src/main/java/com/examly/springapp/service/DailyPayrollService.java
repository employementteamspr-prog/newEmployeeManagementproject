package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.DailyPayroll;

public interface DailyPayrollService {

    // EXISTING METHODS (UNCHANGED)
    DailyPayroll addDailyPayroll(DailyPayroll dailyPayroll);
    List<DailyPayroll> addAllDailyPayrolls(List<DailyPayroll> dailyPayrolls);
    List<DailyPayroll> getAllDailyPayroll();
    Optional<DailyPayroll> getDailyPayrollById(Long id);
    Optional<DailyPayroll> getDailyPayrollByBasicSalary(double basicSalary);
    Optional<DailyPayroll> getDailyPayrollByNetSalary(double netSalary);
    DailyPayroll updateDailyPayroll(Long id, DailyPayroll dailyPayroll);
    boolean deleteDailyPayrollById(Long id);

    // ✅ NEW METHODS (ONLY ADDED)
    Page<DailyPayroll> getDailyPayrollsByPagination(int pageNo, int pageSize);
    Page<DailyPayroll> getDailyPayrollsByPaginationAndSorting(int pageNo, int pageSize, String sortBy, String direction);

    List<DailyPayroll> sortByDpayrollId();
    List<DailyPayroll> sortByDbasicSalary();
    List<DailyPayroll> sortByDdate();
}
