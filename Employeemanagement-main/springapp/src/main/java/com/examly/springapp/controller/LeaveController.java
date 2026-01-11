package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.examly.springapp.model.Leave;
import com.examly.springapp.service.LeaveService;

@RestController
@RequestMapping("/api")
public class LeaveController {
    @Autowired 
    private LeaveService leaveService;

    // POST ONE LEAVE
    @PostMapping("/leave")
    public ResponseEntity<Leave> applyLeave(@RequestBody Leave leave) {
        try{
            Leave savedLeave = leaveService.saveLeave(leave);
            return new ResponseEntity<>(savedLeave, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //POST MULTIPLE LEAVE
    @PostMapping("/leaves")
    public ResponseEntity<List<Leave>> applyMultipleLeaves(@RequestBody List<Leave> leave) {
        try{
            List<Leave> savedLeaves = leaveService.saveAllLeave(leave);
            return new ResponseEntity<>(savedLeaves, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //GET ALL LEAVES
    @GetMapping("/leaves")
    public ResponseEntity<List<Leave>> getAllLeaves(){
        List<Leave> leaves=leaveService.getAllLeave();
        if(leaves.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(leaves,HttpStatus.OK);
    }

    //GET LEAVE BY ID
    @GetMapping("/leave/{leaveId}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable int leaveId){
        Leave leave=leaveService.getByLeaveId(leaveId);
        if(leave==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(leave,HttpStatus.OK);
    }

    //GET LEAVES BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Leave>> getLeavesByStatus(@PathVariable String status){
        List<Leave> leaves=leaveService.getLeaveByStatus(status);
        if(leaves.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(leaves,HttpStatus.OK);
    }

    //UPDATE LEAVE BY ID
    @PutMapping("/leave/{leaveId}")   
    public ResponseEntity<Leave> updateLeaveById(@PathVariable int leaveId, @RequestBody Leave leave){
        Leave updatedLeave=leaveService.updateLeaveById(leaveId, leave);
        if(updatedLeave==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedLeave,HttpStatus.OK);
    }

    //DELETE LEAVE BY ID
    @DeleteMapping("/leave/{leaveId}")    
    public ResponseEntity<String> deleteLeaveById(@PathVariable int leaveId){
        boolean isDeleted=leaveService.deleteLeaveById(leaveId);
        if(isDeleted){
            return new ResponseEntity<>("Leave with ID "+leaveId+" deleted successfully.",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete all
    @DeleteMapping("/leaves")
        public ResponseEntity<Void> deleteAllLeaves(){
            leaveService.deleteAllLeaves();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    //BY PAGINATION AND SORTING
    @GetMapping("/leave/page_sort")
    public ResponseEntity<List<Leave>> getLeaves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String field,
            @RequestParam(defaultValue = "asc") String direction) {

            List<Leave> leaves = leaveService.getLeaveByPaginationAndSorting(page, size, field, direction);
            return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
    //pagination
    @GetMapping("/leave/page")
    public ResponseEntity<List<Leave>> getLeavesByPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

            List<Leave> leaves = leaveService.getLeaveByPagination(page, size);
            return new ResponseEntity<>(leaves, HttpStatus.OK);
        }

    //Sorting
    @GetMapping("/leave/sort")
    public ResponseEntity<List<Leave>> getLeavesBySort(
        @RequestParam(defaultValue = "id") String field,
        @RequestParam(defaultValue = "asc") String direction) {

            List<Leave> leaves = leaveService.getBySorting(field, direction);
            return new ResponseEntity<>(leaves, HttpStatus.OK);
        }


    }

