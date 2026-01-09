package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public Document updateDocumentById(Long id) {

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

}
