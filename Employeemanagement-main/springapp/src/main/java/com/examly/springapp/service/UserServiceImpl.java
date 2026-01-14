package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    // ===================== POST =====================

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> saveAllUsers(List<User> users) {
        return userRepo.saveAll(users);
    }

    // ===================== GET =====================

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepo.findByRole(role);
    }

    // ===================== PUT =====================

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepo.save(existingUser);
    }

    // ===================== DELETE =====================

    @Override
    public boolean deleteUserById(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // ===================== PAGINATION =====================

    @Override
    public Page<User> getUsersByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable);
    }

    // ===================== PAGINATION WITH SORTING =====================

    @Override
    public Page<User> getUsersByPaginationAndSorting(
            int page,
            int size,
            String field,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepo.findAll(pageable);
    }

    // ===================== SORTING (GENERIC) =====================

    @Override
    public List<User> getUsersBySorting(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        return userRepo.findAll(sort);
    }

    // ===================== SORTING BY INDIVIDUAL FIELDS =====================

    @Override
    public List<User> sortByUserId() {
        Sort sortById = Sort.by("userId").ascending();
        return userRepo.findAll(sortById);
    }

    @Override
    public List<User> sortByUsername() {
        Sort sortByUsername = Sort.by("username").ascending();
        return userRepo.findAll(sortByUsername);
    }

    @Override
    public List<User> sortByRole() {
        Sort sortByRole = Sort.by("role").ascending();
        return userRepo.findAll(sortByRole);
    }
}
