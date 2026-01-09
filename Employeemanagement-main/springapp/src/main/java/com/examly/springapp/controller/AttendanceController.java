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

    @PostMapping("/add")
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceService.addAttendence(attendance);
    }

    @GetMapping("/get/{id}")
    public Attendance getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendenceById(id);
    }

    @GetMapping("/getAll")
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendences();
    }

    @PutMapping("/update/{id}")
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        return attendanceService.updateAttendence(id, attendance);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteAttendance(@PathVariable Long id) {
        return attendanceService.deleteAttendence(id);
    }

    @GetMapping("/getByDate/{date}")
    public List<Attendance> getAttendanceByDate(@PathVariable String date) {
        return attendanceService.getAttendenceByDate(date);
    }
}