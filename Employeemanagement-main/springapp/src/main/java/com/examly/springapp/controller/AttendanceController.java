package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Attendance;
import com.examly.springapp.service.AttendanceService;
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    // Add Attendance
    @PostMapping("/add")
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendence(attendance);
    }
    // Add Multiple Attendances
    @PostMapping("/addMultiple")
    public List<Attendance> addMultipleAttendances(@RequestBody List<Attendance> attendances) {
        return attendanceService.addAttendences(attendances);
    }
    // Get Attendance By Id
    @GetMapping("/get/{id}")
    public Attendance getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendenceById(id);
    }

    // Get All Attendances
    @GetMapping("/getAll")
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendences();
    }

    // Update Attendance
    @PutMapping("/update/{id}")
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        return attendanceService.updateAttendence(id, attendance);
    }
    // Delete Attendance
    @DeleteMapping("/delete/{id}")
    public boolean deleteAttendance(@PathVariable Long id) {
        return attendanceService.deleteAttendence(id);
    }
    // Get Attendance By Date
    @GetMapping("/getByDate/{date}")
    public List<Attendance> getAttendanceByDate(@PathVariable String date) {
        return attendanceService.getAttendenceByDate(date);
    }

    // Get Attendance By ClockInTime
    @GetMapping("/getByClockInTime/{clockInTime}")
    public List<Attendance> getAttendanceByClockInTime(@PathVariable String clockInTime) {
        return attendanceService.getAttendancesByClockInTime(clockInTime);
    }

    // Get Attendance By ClockOutTime
    @GetMapping("/getByClockOutTime/{clockOutTime}")
    public List<Attendance> getAttendanceByClockOutTime(@PathVariable String clockOutTime) {
        return attendanceService.getAttendancesByClockOutTime(clockOutTime);
    }
}