package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserService {
    @Autowired UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getByUserId(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }
    
}
