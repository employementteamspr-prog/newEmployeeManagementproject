package com.examly.springapp.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Task;
import com.examly.springapp.repository.TaskRepo;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    // ================= POST =================

    @Override
    public Task addTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public List<Task> addAllTasks(List<Task> tasks) {
        return taskRepo.saveAll(tasks);
    }

    // ================= GET =================

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
    }

    @Override
    public Optional<Task> getTaskByEmployeeId(Long employeeId) {
        return taskRepo.findByEmployeeId(employeeId);
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepo.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByDeadline(Date deadline) {
        return taskRepo.findByDeadline(deadline);
    }

    // ================= PUT =================

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setEmployeeId(updatedTask.getEmployeeId());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDeadline(updatedTask.getDeadline());

        return taskRepo.save(task);
    }

    // ================= DELETE =================

    @Override
    public boolean deleteTaskById(Long id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // ================= PAGINATION =================

    @Override
    public Page<Task> getTasksByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepo.findAll(pageable);
    }

    // ================= PAGINATION WITH SORTING =================

    @Override
    public Page<Task> getTasksByPaginationAndSorting(int page,
            int size,
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return taskRepo.findAll(pageable);
    }
    
    
    // ================= SORTING =================
    @Override
    public List<Task> getTasksBySorting(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return taskRepo.findAll(sort);
    }

    @Override
    public List<Task> sortByTaskId() {
        return taskRepo.findAll(Sort.by("taskId").ascending());
    }

    @Override
    public List<Task> sortByDeadline() {
        return taskRepo.findAll(Sort.by("deadline").ascending());
    }

    @Override
    public List<Task> sortByStatus() {
        return taskRepo.findAll(Sort.by("status").ascending());
    }
}
