package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Leave;
import com.examly.springapp.repository.LeaveRepo;

@Service
public class LeaveServiceImpl implements LeaveService{

    @Autowired LeaveRepo leaveRepo;

    //POST ONE BY ONE
    @Override
    public Leave saveLeave(Leave leave){
        return leaveRepo.save(leave);

    }
    //POST ALL 
    @Override
    public List<Leave> saveAllLeave(List<Leave> leave){
        return leaveRepo.saveAll(leave);

    }
    
    //GET ALL
    @Override
    public List<Leave> getAllLeave(){
        return leaveRepo.findAll();

    }

    //GET BY LEAVEID
    @Override
    public Leave getByLeaveId(int leaveId){
        return leaveRepo.findById(leaveId).orElse(null);
    }

    //GET BY STATUS
    @Override
    public List<Leave> getLeaveByStatus(String status){
        return leaveRepo.findLeaveByStatus(status);
    }

    //PUT BY LEAVEID
    @Override
    public Leave updateLeaveById(int leaveId,Leave leave){
        Leave existingLeave=leaveRepo.findById(leaveId).orElse(null);
        if (existingLeave == null) {
        return null; 
        }
        existingLeave.setLeaveType(leave.getLeaveType());
        existingLeave.setStartDate(leave.getStartDate());
        existingLeave.setEndDate(leave.getEndDate());
        existingLeave.setReason(leave.getReason());
        existingLeave.setStatus(leave.getStatus());
        return leaveRepo.save(existingLeave);
    }
    //DELETE BY LEAVEID
    @Override
    public boolean deleteLeaveById(int leaveId){
        if(leaveRepo.existsById(leaveId)){
            leaveRepo.deleteById(leaveId);
            return true;
        }
        return false;
    }

    //DELETE ALL LEAVES
    @Override
    public void deleteAllLeaves(){
        leaveRepo.deleteAll();
    }

    //pagination
    @Override
    public Page<Leave> getLeaveByPagination(int offset,int size){
        Pageable pageable=PageRequest.of(offset, size);
        return leaveRepo.findAll(pageable);
    }

    //Pagination and Sorting
    @Override
    public Page<Leave> getLeaveByPaginationAndSorting(int offset,int size,String field,String direction){
        Sort sort= direction.equalsIgnoreCase("desc")?
        Sort.by(field).descending():
        Sort.by(field).ascending();
        Pageable pageable=PageRequest.of(offset,size,sort);
        return leaveRepo.findAll(pageable);
    }

    //Sorting only by field asc &desc
    @Override
    public List<Leave> getBySorting(String field,String direction){
        Sort sortByField=direction.equalsIgnoreCase("desc")?
        Sort.by(field).descending():
        Sort.by(field).ascending();
        return leaveRepo.findAll(sortByField);
    }

    //Sorting by ID
     @Override
    public List<Leave> getSortingById(){
        Sort sortById=Sort.by("leaveId").ascending();
        return leaveRepo.findAll(sortById);
    }
    //Sorting by Type
     @Override
    public List<Leave> getSortingByType(){
        Sort sortByType=Sort.by("leaveType").ascending();
        return leaveRepo.findAll(sortByType);
    }
    //Sorting by StartDate
     @Override
    public List<Leave> getSortingByStartDate(){
        Sort sortByStartDate=Sort.by("startDate").ascending();
        return leaveRepo.findAll(sortByStartDate);
    }
    //Sorting by EndDate
     @Override
    public List<Leave> getSortingByEndDate(){
        Sort sortByEndDate=Sort.by("endDate").ascending();
        return leaveRepo.findAll(sortByEndDate);
    }
    //Sorting by Status
     @Override
    public List<Leave> getSortingByStatus(){
        Sort sortByStatus=Sort.by("status").ascending();
        return leaveRepo.findAll(sortByStatus);
    }
    //Sorting by Reason
    @Override
    public List<Leave> getSortingByReason(){
        Sort sortByReason=Sort.by("reason").ascending();
        return leaveRepo.findAll(sortByReason);
    }

}
