package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.DailyPayroll;
import com.examly.springapp.repository.DailyPayrollRepo;

@Service
public class DailyPayrollServiceImpl implements DailyPayrollService {

    @Autowired
    private DailyPayrollRepo dailyPayrollRepo;

    // ===================== EXISTING CRUD METHODS =====================

    @Override
    public DailyPayroll addDailyPayroll(DailyPayroll dailyPayroll) {
        return dailyPayrollRepo.save(dailyPayroll);
    }

    @Override
    public List<DailyPayroll> addAllDailyPayrolls(List<DailyPayroll> dailyPayrolls) {
        return dailyPayrollRepo.saveAll(dailyPayrolls);
    }

    @Override
    public List<DailyPayroll> getAllDailyPayroll() {
        return dailyPayrollRepo.findAll();
    }

    @Override
    public Optional<DailyPayroll> getDailyPayrollById(Long id) {
        return dailyPayrollRepo.findById(id);
    }

    @Override
    public Optional<DailyPayroll> getDailyPayrollByBasicSalary(double basicSalary) {
        return dailyPayrollRepo.findByDbasicSalary(basicSalary);
    }

    @Override
    public Optional<DailyPayroll> getDailyPayrollByNetSalary(double netSalary) {
        return dailyPayrollRepo.findByDnetSalary(netSalary);
    }

    @Override
    public DailyPayroll updateDailyPayroll(Long id, DailyPayroll updatedPayroll) {
        DailyPayroll existingPayroll = dailyPayrollRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DailyPayroll not found with id: " + id));

        existingPayroll.setdbasicSalary(updatedPayroll.getdbasicSalary());
        existingPayroll.setddeductions(updatedPayroll.getddeductions());
        existingPayroll.setdnetSalary(updatedPayroll.getdnetSalary());
        existingPayroll.setddate(updatedPayroll.getddate());

        return dailyPayrollRepo.save(existingPayroll);
    }

    @Override
    public boolean deleteDailyPayrollById(Long id) {
        if (dailyPayrollRepo.existsById(id)) {
            dailyPayrollRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // ===================== PAGINATION AND SORTING =====================

    @Override
    public Page<DailyPayroll> getDailyPayrollsByPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return dailyPayrollRepo.findAll(pageable);
    }

    @Override
    public Page<DailyPayroll> getDailyPayrollsByPaginationAndSorting(int pageNo, int pageSize, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return dailyPayrollRepo.findAll(pageable);
    }

    @Override
    public List<DailyPayroll> sortByDpayrollId() {
        return dailyPayrollRepo.findAll(Sort.by("dpayrollId"));
    }

    @Override
    public List<DailyPayroll> sortByDbasicSalary() {
        return dailyPayrollRepo.findAll(Sort.by("dbasicSalary"));
    }

    @Override
    public List<DailyPayroll> sortByDdate() {
        return dailyPayrollRepo.findAll(Sort.by("ddate"));
    }
}
