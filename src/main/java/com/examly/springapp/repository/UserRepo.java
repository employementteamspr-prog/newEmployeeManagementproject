package com.examly.springapp.repository;

import com.examly.springapp.model.Employee;
import com.examly.springapp.model.User;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface UserRepo extends JpaRepository<User,Integer> {

}
