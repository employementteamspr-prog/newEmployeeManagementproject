package com.examly.springapp.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Task;
import com.examly.springapp.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task newTask = taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }
    @PostMapping("/batch")
    public ResponseEntity<List<Task>> addMultipleTasks( @RequestBody List<Task> tasks) {
        return ResponseEntity.status(HttpStatus.CREATED) .body(taskService.addAllTasks(tasks));
    }
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Task> getTaskByEmployeeId(@PathVariable Long employeeId) {
        Optional<Task> task=taskService.getTaskByEmployeeId(employeeId);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskService.getTasksByStatus(status);
    }
    @GetMapping("/deadline/{deadline}")
    public List<Task> getTasksByDeadline(@PathVariable Date deadline) {
        return taskService.getTasksByDeadline(deadline);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById( @PathVariable Long id) {
        boolean deleted = taskService.deleteTaskById(id);
        if (deleted) {
            return ResponseEntity.ok("Task deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    }
}
