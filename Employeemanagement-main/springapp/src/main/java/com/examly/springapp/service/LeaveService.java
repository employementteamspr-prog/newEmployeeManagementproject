package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Leave;

@Service

public interface LeaveService {
     // SAVE ONE LEAVE
    Leave saveLeave(Leave leave);

    // SAVE MULTIPLE LEAVE
    List<Leave> saveAllLeave(List<Leave> leave);

    // GET ALL LEAVE
    List<Leave> getAllLeave();

    //GET LEAVE BY ID
    Leave getByLeaveId(int leaveId);

    //GET LEAVES BY STATUS
    List<Leave> getLeaveByStatus(String status);

    //UPDATE LEAVE BY ID
    Leave updateLeaveById(int leaveId, Leave leave);
    
    //DELETE LEAVE BY ID
    boolean deleteLeaveById(int leaveId);

    //DELETE ALL LEAVES
    void deleteAllLeaves();

    //Pagination
    List<Leave> getLeaveByPagination(int offset,int size);

    //Pagination and Sorting
    List<Leave> getLeaveByPaginationAndSorting(int offset,int size,String field,String direction);

    //Sorting only by field asc &desc
    List<Leave> getBySorting(String field,String direction);
}
    
