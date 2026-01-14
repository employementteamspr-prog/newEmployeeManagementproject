package com.examly.springapp.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.Task;

public interface TaskService {

    // POST
    Task addTask(Task task);
    List<Task> addAllTasks(List<Task> tasks);

    // GET
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long id);
    Optional<Task> getTaskByEmployeeId(Long employeeId);
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksByDeadline(Date deadline);

    // PUT
    Task updateTask(Long id, Task task);

    // DELETE
    boolean deleteTaskById(Long id);

    // PAGINATION
    Page<Task> getTasksByPagination(int page, int size);

    // PAGINATION WITH SORTING
    Page<Task> getTasksByPaginationAndSorting(int page, int size, String field, String direction);
    
     List<Task> getTasksBySorting(String field, String direction);
    // SORTING
    List<Task> sortByTaskId();
    List<Task> sortByDeadline();
    List<Task> sortByStatus();
}
