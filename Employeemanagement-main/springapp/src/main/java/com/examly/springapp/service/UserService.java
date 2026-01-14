package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.User;

public interface UserService {

    // ===================== POST =====================
    User saveUser(User user);
    List<User> saveAllUsers(List<User> users);

    // ===================== GET =====================
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    List<User> getUsersByRole(String role);

    // ===================== PUT =====================
    User updateUser(Long id, User user);

    // ===================== DELETE =====================
    boolean deleteUserById(Long id);

    // ===================== PAGINATION =====================
    Page<User> getUsersByPagination(int page, int size);

    // ===================== PAGINATION WITH SORTING =====================
    Page<User> getUsersByPaginationAndSorting(
            int page,
            int size,
            String field,
            String direction);

    // ===================== SORTING =====================
    List<User> getUsersBySorting(String field, String direction);

    // ===================== SORTING BY INDIVIDUAL FIELDS =====================
    List<User> sortByUserId();
    List<User> sortByUsername();
    List<User> sortByRole();
}
