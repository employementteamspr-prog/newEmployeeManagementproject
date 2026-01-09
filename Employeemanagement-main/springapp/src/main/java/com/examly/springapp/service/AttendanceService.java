package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Attendance;

public interface AttendanceService {
    Attendance addAttendence(Attendance attendence);
    Attendance getAttendenceById(Long attendenceId);
    List<Attendance> getAllAttendences();
    Attendance updateAttendence(Long attendenceId, Attendance attendence);
    boolean deleteAttendence(Long   attendenceId);
    List<Attendance> getAttendenceByDate(String date);
}
