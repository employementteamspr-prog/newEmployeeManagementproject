package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ===================== POST =====================

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // ===================== GET =====================

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ===================== PUT =====================

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // ===================== DELETE =====================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    // ===================== PAGINATION =====================

    @GetMapping("/page")
    public ResponseEntity<Page<User>> getUsersByPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<User> users = userService.getUsersByPagination(page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // ===================== PAGINATION + SORTING =====================

    @GetMapping("/page_sort")
    public ResponseEntity<Page<User>> getUsersByPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "userId") String field,
            @RequestParam(defaultValue = "asc") String direction) {

        Page<User> users = userService.getUsersByPaginationAndSorting(
                page, size, field, direction);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // ===================== SORTING (GENERIC) =====================

    @GetMapping("/sort")
    public ResponseEntity<List<User>> getUsersBySorting(
            @RequestParam(defaultValue = "userId") String field,
            @RequestParam(defaultValue = "asc") String direction) {

        List<User> users = userService.getUsersBySorting(field, direction);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // ===================== SORTING BY INDIVIDUAL FIELDS =====================

    @GetMapping("/sort/userId")
    public ResponseEntity<List<User>> sortByUserId() {
        return new ResponseEntity<>(userService.sortByUserId(), HttpStatus.OK);
    }

    @GetMapping("/sort/username")
    public ResponseEntity<List<User>> sortByUsername() {
        return new ResponseEntity<>(userService.sortByUsername(), HttpStatus.OK);
    }

    @GetMapping("/sort/role")
    public ResponseEntity<List<User>> sortByRole() {
        return new ResponseEntity<>(userService.sortByRole(), HttpStatus.OK);
    }
}
