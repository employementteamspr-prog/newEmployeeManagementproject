package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Allowance;
import com.examly.springapp.repository.AllowanceRepo;

@Service
public class AllowanceServiceImpl implements AllowanceService {
    @Autowired
    private AllowanceRepo allowanceRepo;

    //POST SINGLE ALLOWANCE
    @Override
    public Allowance addAllowance(Allowance allowance){
        return allowanceRepo.save(allowance);
    }
    
    //POST ALL ALLOWANCES
    @Override
    public List<Allowance> addAllowances(List<Allowance> allowances){
        return allowanceRepo.saveAll(allowances);
    }

    //GET ALL ALLOWANCES
    @Override
    public List<Allowance> getAllAllowances(){
        return allowanceRepo.findAll();
    }

    //GET ALLOWANCE BY ALLOWANCEID
    @Override
    public Allowance getAllowanceById(int allowanceId){
        return allowanceRepo.findById(allowanceId).orElse(null);
    }
    
    //GET BY AMOUNT
    @Override
    public List<Allowance> getByAmount(double amount){
        return allowanceRepo.findByAmount(amount);
    }
    
    //GET BY FREQUENCY
    @Override
    public List<Allowance> getByFrequency(String frequency) {
    return allowanceRepo.findByFrequency(frequency);
    }

    //UPDATE ALLOWANCE BY ALLOWANCEID
    @Override
    public Allowance updateAllowanceById(int allowanceId, Allowance allowance){
        Allowance existingAllowance=allowanceRepo.findById(allowanceId).orElse(null);
        if(existingAllowance==null){
            return null;
        }
        existingAllowance.setAllowanceType(allowance.getAllowanceType());
        existingAllowance.setAmount(allowance.getAmount());
        existingAllowance.setFrequency(allowance.getFrequency());
        return allowanceRepo.save(existingAllowance);
    }
    //DELETE ALLOWANCE BY ALLOWANCEID
    @Override
    public boolean deleteAllowanceById(int allowanceId){
        if(allowanceRepo.existsById(allowanceId)){
            allowanceRepo.deleteById(allowanceId);
            return true;
        }
        return false;
    }
    
}