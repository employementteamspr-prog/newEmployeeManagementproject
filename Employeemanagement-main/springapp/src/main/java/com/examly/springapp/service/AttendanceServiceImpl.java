
package com.examly.springapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Attendance;
import com.examly.springapp.repository.AttendanceRepo;

@Service
public class AttendanceServiceImpl implements AttendanceService
{
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
            existingAttendence.setClockInTime(attendence.getClockInTime());
            existingAttendence.setClockOutTime(attendence.getClockOutTime());
            existingAttendence.setLocation(attendence.getLocation());
            
          
            
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
    public List<Attendance> addAttendences(List<Attendance> attendences) {
        return attendanceRepo.saveAll(attendences);
    }

    // @Override
    // public List<Attendance> getAttendancesByClockInTime(String clockInTime) {
    //     return attendanceRepo.findByClockInTime(clockInTime);
    // }

    // @Override
    // public List<Attendance> getAttendancesByClockOutTime(String clockOutTime) {
    //     return attendanceRepo.findByClockOutTime(clockOutTime);
    // }

    @Override
    public Attendance clockIn(Attendance attendance) {
        attendance.setClockInTime(LocalTime.now());
        attendance.setDate(LocalDate.now());
        return attendanceRepo.save(attendance);
    }

    @Override
    public Attendance clockOut(Long id) {
        Attendance attendance = attendanceRepo.findById(id).orElse(null);
        if (attendance != null) {
            attendance.setClockOutTime(LocalTime.now());
            return attendanceRepo.save(attendance);
        }
        return null;
    }
    
}

