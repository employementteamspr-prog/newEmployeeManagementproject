package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import com.examly.springapp.model.Document;

public interface DocumentService {
       Document saveDocument(Document document);
       
       List<Document> savDocuments(List<Document> documents);

       List<Document> getDocuments();

       Document getDocumentById(Long id);

       Document getDocumentByName(String name);

       Document getDocumentByDate(LocalDate localDate);

       Document updateDocumentById(Long id);

       boolean deleteDocuments();

       boolean deleteDocumentById(Long id);
}