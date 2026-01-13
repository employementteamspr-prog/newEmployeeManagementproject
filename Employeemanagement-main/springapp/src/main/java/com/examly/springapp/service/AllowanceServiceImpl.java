package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.examly.springapp.model.Allowance;
import com.examly.springapp.repository.AllowanceRepo;
@Service
public class AllowanceServiceImpl implements AllowanceService {

    @Autowired
    private AllowanceRepo allowanceRepo;

    // POST SINGLE ALLOWANCE
    @Override
    public Allowance addAllowance(Allowance allowance){
        return allowanceRepo.save(allowance);
    }

    // POST ALL ALLOWANCES
    @Override
    public List<Allowance> addAllowances(List<Allowance> allowances){
        return allowanceRepo.saveAll(allowances);
    }

    // GET ALL ALLOWANCES
    @Override
    public List<Allowance> getAllAllowances(){
        return allowanceRepo.findAll();
    }

    // GET BY ID
    @Override
    public Allowance getAllowanceById(int allowanceId){
        return allowanceRepo.findById(allowanceId).orElse(null);
    }

    // GET BY AMOUNT
    @Override
    public List<Allowance> getByAmount(double amount){
        return allowanceRepo.findByAmount(amount);
    }

    // GET BY FREQUENCY
    @Override
    public List<Allowance> getByFrequency(String frequency){
        return allowanceRepo.findByFrequency(frequency);
    }

    // UPDATE BY ID
    @Override
    public Allowance updateAllowanceById(int allowanceId, Allowance allowance){
        Allowance existing = allowanceRepo.findById(allowanceId).orElse(null);
        if(existing == null) return null;

        existing.setAllowanceType(allowance.getAllowanceType());
        existing.setAmount(allowance.getAmount());
        existing.setFrequency(allowance.getFrequency());

        return allowanceRepo.save(existing);
    }

    // DELETE BY ID
    @Override
    public boolean deleteAllowanceById(int allowanceId){
        if(allowanceRepo.existsById(allowanceId)){
            allowanceRepo.deleteById(allowanceId);
            return true;
        }
        return false;
    }

    // DELETE ALL
    @Override
    public void deleteAllAllowances(){
        allowanceRepo.deleteAll();
    }

    // PAGINATION
    @Override
    public Page<Allowance> getAllowanceByPagination(int offset, int size){
        Pageable pageable = PageRequest.of(offset, size);
        return allowanceRepo.findAll(pageable);
    }

    // PAGINATION WITH SORTING
    @Override
    public Page<Allowance> getBySortingPagination(int offset, int size, String field, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ?
                    Sort.by(field).descending() :
                    Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(offset, size, sort);
        return allowanceRepo.findAll(pageable);
    }

    // Sorting by field asc & desc
    @Override
    public List<Allowance> getBySorting(String field, String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ?
                    Sort.by(field).descending() :
                    Sort.by(field).ascending();

        return allowanceRepo.findAll(sort);
    }

    //sorting by individual field
    @Override
    public List<Allowance> getBySortingID(){
        Sort sortById=Sort.by("allowanceId").ascending();
        return allowanceRepo.findAll(sortById);

    }

    //sorting  by type
    @Override
    public List<Allowance> getBySortingType(){
        Sort sortByType=Sort.by("allowanceType").ascending();
        return allowanceRepo.findAll(sortByType);

    }
    //sorting by amount
    @Override
    public List<Allowance> getBySortingAmount(){
        Sort sortByAmount=Sort.by("amount").ascending();
        return allowanceRepo.findAll(sortByAmount);

    }
    //sorting by frequency
    @Override   
    public List<Allowance> getBySortingFrequency(){
        Sort sortByFrequency=Sort.by("frequency").ascending();
        return allowanceRepo.findAll(sortByFrequency);

    }
}
