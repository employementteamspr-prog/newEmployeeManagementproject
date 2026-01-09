package com.examly.springapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Document;
import com.examly.springapp.service.DocumentService;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> saveDocument(@RequestBody Document document) {
        Document saved = documentService.saveDocument(document);

        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Document>> saveDocuments(@RequestBody List<Document> documents) {
        List<Document> savedDocs = documentService.saveDocuments(documents);

        if (savedDocs != null) {
            return new ResponseEntity<>(savedDocs, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getDocuments();

        if (documents != null) {
            return new ResponseEntity<>(documents, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);

        if (document != null) {
            return new ResponseEntity<>(document, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Document> getDocumentByName(@PathVariable String name) {
        Document document = documentService.getDocumentByName(name);

        if (document != null) {
            return new ResponseEntity<>(document, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Document> getDocumentByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Document document = documentService.getDocumentByDate(date);

        if (document != null) {
            return new ResponseEntity<>(document, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Document> updateDocumentById(@PathVariable Long id) {
        Document updated = documentService.updateDocumentById(id);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteDocumentById(@PathVariable Long id) {
        boolean deleted = documentService.deleteDocumentById(id);

        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllDocuments() {
        boolean deleted = documentService.deleteDocuments();

        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }
}
