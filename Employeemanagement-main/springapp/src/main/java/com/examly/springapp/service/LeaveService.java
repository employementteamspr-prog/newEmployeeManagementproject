package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.Leave;



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
    Page<Leave> getLeaveByPagination(int offset,int size);

    //Pagination and Sorting
    Page<Leave> getLeaveByPaginationAndSorting(int offset,int size,String field,String direction);

    //Sorting only by field asc &desc
    List<Leave> getBySorting(String field,String direction);

    //Sorting by ID
    public List<Leave> getSortingById();

    //Sorting by type
    public List<Leave> getSortingByType();

    //Sorting by Start Date
    public List<Leave> getSortingByStartDate();

    //Sorting by End Date
    public List<Leave> getSortingByEndDate();

    //Sorting by Status
    public List<Leave> getSortingByStatus();

    //Sorting by Reason
    public List<Leave> getSortingByReason();

}
    
