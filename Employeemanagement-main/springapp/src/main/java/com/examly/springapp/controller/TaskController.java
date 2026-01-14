package com.examly.springapp.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Task;
import com.examly.springapp.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ================= POST =================

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.addTask(task));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Task>> addMultipleTasks(@RequestBody List<Task> tasks) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.addAllTasks(tasks));
    }

    // ================= GET =================

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Task> getTaskByEmployeeId(@PathVariable Long employeeId) {
        Optional<Task> task = taskService.getTaskByEmployeeId(employeeId);
        return task.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/deadline/{deadline}")
    public List<Task> getTasksByDeadline(@PathVariable Date deadline) {
        return taskService.getTasksByDeadline(deadline);
    }

    // ================= PAGINATION =================
    @GetMapping("/pagination")
    public Page<Task> getTasksByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return taskService.getTasksByPagination(page, size);
    }

    // ================= PAGINATION WITH SORTING =================
    @GetMapping("/pagination_sort")
    public Page<Task> getTasksByPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "taskId") String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return taskService.getTasksByPaginationAndSorting(page, size, field, direction);
    }

    // ================= SORTING =================
    @GetMapping("/sort")
    public List<Task> getTasksBySorting(
            @RequestParam(defaultValue = "taskId") String field,
            @RequestParam(defaultValue = "asc") String direction) {
        return taskService.getTasksBySorting(field, direction);
    }

    @GetMapping("/sort/taskId")
    public List<Task> sortByTaskId() {
        return taskService.sortByTaskId();
    }

    @GetMapping("/sort/deadline")
    public List<Task> sortByDeadline() {
        return taskService.sortByDeadline();
    }

    @GetMapping("/sort/status")
    public List<Task> sortByStatus() {
        return taskService.sortByStatus();
    }

    // ================= PUT =================

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {
        if (taskService.deleteTaskById(id)) {
            return ResponseEntity.ok("Task deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Task not found");
    }
}
