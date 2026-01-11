package com.examly.springapp.repository;
<<<<<<< HEAD

public class DocumentRepo {
    
}
=======
import java.time.LocalDate;
import java.util.Optional;

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
}
>>>>>>> 8db20400df5488e55e17061a4d64e79d36c54ebe
