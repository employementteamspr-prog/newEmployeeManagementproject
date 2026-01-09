package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int attendanceId;
    private String clockIntTime;
    private String clockoutTime;
    private String location;
    private LocalDate date;

    public Attendance() {
    }

    public Attendance(String clockIntTime, String clockoutTime, LocalDate date, String location) {
        this.clockIntTime = clockIntTime;
        this.clockoutTime = clockoutTime;
        this.date = date;
        this.location = location;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getClockIntTime() {
        return clockIntTime;
    }

    public void setClockIntTime(String clockIntTime) {
        this.clockIntTime = clockIntTime;
    }

    public String getClockoutTime() {
        return clockoutTime;
    }

    public void setClockoutTime(String clockoutTime) {
        this.clockoutTime = clockoutTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
