package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Leave;

@Repository
public interface LeaveRepo extends JpaRepository<Leave,Integer> {
    List<Leave> findLeaveByStatus(String status);
     //Pagination
    Page<Leave> getLeaveByPagination(int offset,int size);

    //Pagination and Sorting
    Page<Leave> getLeaveByPaginationAndSorting(int offset,int size,String field,String direction);
}