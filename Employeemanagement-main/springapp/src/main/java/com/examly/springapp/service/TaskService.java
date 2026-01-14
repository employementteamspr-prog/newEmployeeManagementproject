package com.examly.springapp.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Task;
import com.examly.springapp.repository.TaskRepo;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Task addTask(Task task) {
        return taskRepo.save(task);
    }
    public List<Task> addAllTasks(List<Task> tasks) {
        return taskRepo.saveAll(tasks);
    }
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
    }
    public Optional<Task> getTaskByEmployeeId(Long employeeId) {
        return taskRepo.findByEmployeeId(employeeId);
    }
    public List<Task> getTasksByStatus(String status) {
        return taskRepo.findByStatus(status);
    }
    public List<Task> getTasksByDeadline(Date deadline) {
        return taskRepo.findByDeadline(deadline);
    }
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepo.findById(id).orElseThrow();
        existingTask.setEmployeeId(updatedTask.getEmployeeId());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDeadline(updatedTask.getDeadline());

        return taskRepo.save(existingTask);
    }
    public boolean deleteTaskById(Long id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
