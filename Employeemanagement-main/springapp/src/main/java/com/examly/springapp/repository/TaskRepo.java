package com.examly.springapp.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>{
   Optional<Task> findByEmployeeId(Long employeeId);
   List<Task> findByStatus(String status);
   List<Task> findByDeadline(Date deadline);
     // Sorting
    List<Task> findAll(Sort sort);

    // Pagination + Sorting
    Page<Task> findAll(Pageable pageable);
}