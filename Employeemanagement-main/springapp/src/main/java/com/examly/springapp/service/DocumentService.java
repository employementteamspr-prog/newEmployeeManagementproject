package com.examly.springapp.service;

<<<<<<< HEAD
public class DocumentService {
    
}
=======
import java.time.LocalDate;
import java.util.List;

import com.examly.springapp.model.Document;

public interface DocumentService {
       Document saveDocument(Document document);
       
       List<Document> saveDocuments(List<Document> documents);

       List<Document> getDocuments();

       Document getDocumentById(Long id);

       Document getDocumentByName(String docName);

       Document getDocumentByDate(LocalDate localDate);

       Document updateDocumentById(Long id,Document document);

       boolean deleteDocuments();

       boolean deleteDocumentById(Long id);

       boolean deleteDocumentByName(String name);
}
>>>>>>> 8db20400df5488e55e17061a4d64e79d36c54ebe
