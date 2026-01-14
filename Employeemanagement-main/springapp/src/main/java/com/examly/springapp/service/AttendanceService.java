package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.examly.springapp.model.Attendance;

public interface AttendanceService {
    Attendance addAttendence(Attendance attendence);
    List<Attendance> addAttendences(List<Attendance> attendences);
    Attendance getAttendanceByEmployeeId(Long employeeId);
    List<Attendance> getAllAttendences();
    Attendance getAttendenceById(Long attendenceId);
    Attendance updateAttendence(Long attendenceId, Attendance attendence);
    boolean deleteAttendence(Long   attendenceId);
    
  
    Attendance clockIn(Attendance attendance);
    Attendance clockOut(Long id);

    Page<Attendance> getAttendancesWithPagination(Pageable pageable);
} 
