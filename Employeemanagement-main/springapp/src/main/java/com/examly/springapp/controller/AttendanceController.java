package com.examly.springapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        if (attendanceService.addAttendence(attendance) != null) {
            return ResponseEntity.ok(attendanceService.addAttendence(attendance));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // Add Multiple Attendances
    @PostMapping("/addMultiple")
    public ResponseEntity<List<Attendance>> addMultipleAttendances(@RequestBody List<Attendance> attendances) {
        List<Attendance> addedAttendances = attendanceService.addAttendences(attendances);
        if(addedAttendances != null) {
            return ResponseEntity.ok(addedAttendances);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // Get Attendance By Id
    @GetMapping("/get/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Attendance attendance = attendanceService.getAttendenceById(id);
        if (attendance != null) {
            return ResponseEntity.ok(attendance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get All Attendances
    @GetMapping("/getAll")
    public ResponseEntity<List<Attendance>> getAllAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendences();
        if (attendances != null) {
            return ResponseEntity.ok(attendances);
        } else {
            return ResponseEntity.notFound().build();
        }   
    }

    // Update Attendance
    @PutMapping("/update/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        Attendance updatedAttendance = attendanceService.updateAttendence(id, attendance);
        if (updatedAttendance != null) {
            return ResponseEntity.ok(updatedAttendance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete Attendance
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteAttendance(@PathVariable Long id) {
        boolean deleted = attendanceService.deleteAttendence(id);
        if (deleted) {
            return ResponseEntity.ok(deleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
   
    // // Get Attendance By ClockInTime
    // @GetMapping("/getByClockInTime/{clockInTime}")
    // public ResponseEntity<List<Attendance>> getAttendanceByClockInTime(@PathVariable String clockInTime) {
    //     List<Attendance> attendances = attendanceService.getAttendancesByClockInTime(clockInTime);
    //     if (attendances != null) {
    //         return ResponseEntity.ok(attendances);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    

    // // Get Attendance By ClockOutTime
    // @GetMapping("/getByClockOutTime/{clockOutTime}")
    // public ResponseEntity<List<Attendance>> getAttendanceByClockOutTime(@PathVariable String clockOutTime) {
    //     List<Attendance> attendances = attendanceService.getAttendancesByClockOutTime(clockOutTime);
    //     if (attendances != null) {
    //         return ResponseEntity.ok(attendances);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // CLOCK IN
    @PostMapping("/clockIn")
    public ResponseEntity<Attendance> clockIn(@RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.clockIn(attendance));
    }

    // CLOCK OUT
    @PutMapping("/clockOut/{id}")
    public ResponseEntity<Attendance> clockOut(@PathVariable Long id) {
        Attendance updated = attendanceService.clockOut(id);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // Pagination + Sorting
    @GetMapping("/paged")
public ResponseEntity<Page<Attendance>> getAttendancePaged(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "date") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);
    return ResponseEntity.ok(attendanceService.getAttendancesWithPagination(pageable));
}
}