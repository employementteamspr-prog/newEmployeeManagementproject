package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.examly.springapp.model.Document;
import com.examly.springapp.repository.DocumentRepo;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepo documentRepository;

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public List<Document> saveDocuments(List<Document> documents) {
        return documentRepository.saveAll(documents);
    }

    @Override
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Document getDocumentById(Long id) {
        Optional<Document> document = documentRepository.findById(id);

        if (document.isPresent()) {
            return document.get();
        } else {
            return null;
        }
    }

    @Override
    public Document getDocumentByName(String docName) {
        Document document = documentRepository.findBydocName(docName);

        if (document != null) {
            return document;
        } else {
            return null;
        }
    }

    @Override
    public Document getDocumentByDate(LocalDate localDate) {
        Document document = documentRepository.findByUploadDate(localDate);

        if (document != null) {
            return document;
        } else {
            return null;
        }
    }

   @Override
public Document updateDocumentById(Long id,Document document1) {

    if (id != null) {
        Document document = documentRepository.findById(id).orElse(null);

        if (document != null) {
            return documentRepository.save(document);
        } else {
            return null;
        }
    } else {
        return null;
    }
}

@Override
public boolean deleteDocuments() {

    List<Document> documents = documentRepository.findAll();

    if (documents != null && !documents.isEmpty()) {
        documentRepository.deleteAll();
        return true;
    } else {
        return false;
    }
}

@Override
public boolean deleteDocumentById(Long id) {

    if (id != null) {
        Document document = documentRepository.findById(id).orElse(null);

        if (document != null) {
            documentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}

@Override
public boolean deleteDocumentByName(String name) {
   
    if (name != null) {
        Document document = documentRepository.findBydocName(name);

        if (document != null) {
            documentRepository.deleteBydocName(name);
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}
// ---------------- PAGINATION ONLY ----------------
    @Override
    public Page<Document> getDocumentsWithPagination(int page, int size) {
        return documentRepository.findAll(PageRequest.of(page, size));
    }

    // ---------------- SORTING ONLY ----------------
    @Override
    public List<Document> getDocumentsSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();
        return documentRepository.findAll(sort);
    }

    // -------- PAGINATION + SORTING --------
    @Override
    public Page<Document> getDocumentsWithPaginationAndSorting(
            int page, int size, String field, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        Pageable pageable= PageRequest.of(page, size, sort);
        return documentRepository.findAll(pageable);
    }

    // -------- SORT BY FIXED FIELDS --------
    @Override
    public List<Document> sortByDocId(String direction) {
        return documentRepository.findAll(
                direction.equalsIgnoreCase("desc")
                        ? Sort.by("docId").descending()
                        : Sort.by("docId").ascending());
    }

    @Override
    public List<Document> sortByDocName(String direction) {
        return documentRepository.findAll(
                direction.equalsIgnoreCase("desc")
                        ? Sort.by("docName").descending()
                        : Sort.by("docName").ascending());
    }

    @Override
    public List<Document> sortByUploadDate(String direction) {
        return documentRepository.findAll(
                direction.equalsIgnoreCase("desc")
                        ? Sort.by("uploadDate").descending()
                        : Sort.by("uploadDate").ascending());
    }

    
}
