package com.examly.springapp.repository;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Document;

@Repository
public interface DocumentRepo extends JpaRepository<Document,Integer> {
                 
    Document findByDocumentName(String documentName);

    Document findByUploadDate(LocalDate uploadDate);

    Optional<Document> findById(Long id);

    void deleteById(Long id);
}
