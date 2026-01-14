package com.examly.springapp.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>{
   Optional<Task> findByEmployeeId(Long employeeId);
   List<Task> findByStatus(String status);
   List<Task> findByDeadline(Date deadline);
}