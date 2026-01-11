package com.examly.springapp.repository;


import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.query.Page;
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

    // ✅ Pagination + Sorting support
    Page<Document> findAll(Pageable pageable);
}
