package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Attendance;

public interface  AttendanceRepo extends JpaRepository<Attendance, Long>{

    public List<Attendance> findByDate(String date);

    public List<Attendance> findByClockInTime(String clockInTime);

    public List<Attendance> findByClockOutTime(String clockOutTime);

    
}
