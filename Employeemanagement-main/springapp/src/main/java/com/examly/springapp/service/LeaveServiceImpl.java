package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        return leaveRepo.save(leave);
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


}
