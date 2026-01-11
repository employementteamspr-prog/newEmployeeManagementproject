package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.DailyPayroll;
import com.examly.springapp.repository.DailyPayrollRepo;

@Service
public class DailyPayrollService {

    @Autowired
    private DailyPayrollRepo dailyPayrollRepo;
    public DailyPayroll addDailyPayroll(DailyPayroll dailyPayroll) {
        return dailyPayrollRepo.save(dailyPayroll);
    }
    public List<DailyPayroll> addAllDailyPayrolls(List<DailyPayroll> dailyPayrolls) {
        return dailyPayrollRepo.saveAll(dailyPayrolls);
    }
    public List<DailyPayroll> getAllDailyPayroll() {
        return dailyPayrollRepo.findAll();
    }
    public Optional<DailyPayroll> getDailyPayrollById(Long id) {
        return dailyPayrollRepo.findById(id);
    }
    public Optional<DailyPayroll> getDailyPayrollByBasicSalary(double basicSalary) {
        return dailyPayrollRepo.findByDbasicSalary(basicSalary);
    }
    public Optional<DailyPayroll> getDailyPayrollByNetSalary(double netSalary) {
        return dailyPayrollRepo.findByDnetSalary(netSalary);
    }
    public DailyPayroll updateDailyPayroll(Long id, DailyPayroll updatedDailyPayroll) {
        DailyPayroll existingDailyPayroll = dailyPayrollRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DailyPayroll not found with id: " + id));

        existingDailyPayroll.setdbasicSalary(updatedDailyPayroll.getdbasicSalary());
        existingDailyPayroll.setddeductions(updatedDailyPayroll.getddeductions());
        existingDailyPayroll.setdnetSalary(updatedDailyPayroll.getdnetSalary());
        existingDailyPayroll.setddate(updatedDailyPayroll.getddate());

        return dailyPayrollRepo.save(existingDailyPayroll);
    }

    // ===================== DELETE =====================

    // Delete daily payroll by ID (boolean)
    public boolean deleteDailyPayrollById(Long id) {
        if (dailyPayrollRepo.existsById(id)) {
            dailyPayrollRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
