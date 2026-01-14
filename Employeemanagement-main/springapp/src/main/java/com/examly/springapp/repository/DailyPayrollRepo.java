package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.DailyPayroll;


@Repository
public interface DailyPayrollRepo extends JpaRepository<DailyPayroll,Long>{
    Optional<DailyPayroll> findById(Long id);
    Optional<DailyPayroll> findByDbasicSalary(double dbasicSalary);
    Optional<DailyPayroll> findByDnetSalary(double dnetSalary);
      // Sorting
    List<DailyPayroll> findAll(Sort sort);

    // Pagination + Sorting
    Page<DailyPayroll> findAll(Pageable pageable);
}