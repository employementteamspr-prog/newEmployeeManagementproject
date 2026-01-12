package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // ===================== POST =====================

    // Save single user
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    // Save multiple users
    public List<User> saveAllUsers(List<User> users) {
        return userRepo.saveAll(users);
    }

    // ===================== GET =====================

    // Get all users
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get single user by ID
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    // Get single user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    // Get single user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // Get users by role (single or multiple)
    public List<User> getUsersByRole(String role) {
        return userRepo.findByRole(role);
    }

    // ===================== PUT =====================

    // Update user by ID
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());

        return userRepo.save(existingUser);
    }
    public boolean deleteUserById(Long id) {
    if (userRepo.existsById(id)) {
        userRepo.deleteById(id);
        return true;   // delete successful
    }
    return false;      // user not found
}

}
