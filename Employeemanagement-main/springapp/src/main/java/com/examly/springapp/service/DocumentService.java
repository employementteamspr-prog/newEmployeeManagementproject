package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

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
      
       Page<Document> getDocumentsWithPagination(int page, int size);

       List<Document> getDocumentsSorted(String field, String direction);

       Page<Document> getDocumentsWithPaginationAndSorting(
            int page, int size, String field, String direction);

       List<Document> sortByDocId(String direction);

       List<Document> sortByDocName(String direction);

       List<Document> sortByUploadDate(String direction);
}

