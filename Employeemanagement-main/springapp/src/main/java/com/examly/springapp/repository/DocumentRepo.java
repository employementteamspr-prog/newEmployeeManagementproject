package com.examly.springapp.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Document;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Long> {
                 
    

    Optional<Document> findById(Long id);

    void deleteById(Long id);

    void deleteBydocName(String name);

    Document findByUploadDate(LocalDate localDate);

    Document findBydocName(String docName);
   
     // Sorting
    List<Document> findAll(Sort sort);

    // Pagination + Sorting
    Page<Document> findAll(Pageable pageable);
}
