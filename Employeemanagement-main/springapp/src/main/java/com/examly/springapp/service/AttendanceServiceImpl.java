package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Attendance;
import com.examly.springapp.repository.AttendanceRepo;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    private AttendanceRepo attendanceRepo;
    @Override
    public Attendance addAttendence(Attendance attendence) {
        return attendanceRepo.save(attendence);
    }

    @Override
    public Attendance getAttendenceById(Long attendenceId) {
        return attendanceRepo.findById(attendenceId).orElse(null);
    }

    @Override
    public List<Attendance> getAllAttendences() {
        return attendanceRepo.findAll();
    }

    @Override
    public Attendance updateAttendence(Long attendenceId, Attendance attendence) {
        Attendance existingAttendence = attendanceRepo.findById(attendenceId).orElse(null);
        if (existingAttendence != null) {
            existingAttendence.setDate(attendence.getDate());
            existingAttendence.setClockIntTime(attendence.getClockIntTime());
            existingAttendence.setClockoutTime(attendence.getClockoutTime());
            existingAttendence.setLocation(attendence.getLocation());
            
            existingAttendence.setDate(attendence.getDate());
            
            return attendanceRepo.save(existingAttendence);
        }
        return null;
    }

    @Override
    public boolean deleteAttendence(Long attendenceId) {
        attendanceRepo.deleteById(attendenceId);
        return true;
    }

    @Override
    public List<Attendance> getAttendenceByDate(String date) {
        return attendanceRepo.findByDate(date);
    }

    

    
    
}
