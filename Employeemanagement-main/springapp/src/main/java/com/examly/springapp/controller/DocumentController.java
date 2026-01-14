package com.examly.springapp.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.examly.springapp.model.Document;
import com.examly.springapp.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    // ===============================
    // UPLOAD SINGLE DOCUMENT (PDF)
    // ===============================
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> uploadDocument(
            @RequestParam("docId") Long docId,
            @RequestParam("docName") String docName,
            @RequestParam("uploadDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate uploadDate,
            @RequestParam("file") MultipartFile file) {

        try {
            Document document = new Document();
            document.setDocId(docId);
            document.setDocName(docName);
            document.setUploadDate(uploadDate);
            document.setData(file.getBytes());

            Document saved = documentService.saveDocument(document);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===============================
    // UPLOAD MULTIPLE DOCUMENTS
    // ===============================
    @PostMapping(value = "/multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Document>> uploadMultipleDocuments(
            @RequestParam("files") MultipartFile[] files) {

        try {
            List<Document> documents = new ArrayList<>();

            for (MultipartFile file : files) {
                Document doc = new Document();
                doc.setDocName(file.getOriginalFilename());
                doc.setUploadDate(LocalDate.now());
                doc.setData(file.getBytes());
                documents.add(doc);
            }

            List<Document> saved = documentService.saveDocuments(documents);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===============================
    // VIEW DOCUMENT BY ID (INLINE)
    // ===============================
    @GetMapping(value = "/view/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> viewDocument(@PathVariable Long id) {

        Document document = documentService.getDocumentById(id);

        if (document != null && document.getData() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition",
                            "inline; filename=\"" + document.getDocName() + ".pdf\"")
                    .body(document.getData());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ===============================
    // DOWNLOAD DOCUMENT BY ID
    // ===============================
    @GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {

        Document document = documentService.getDocumentById(id);

        if (document != null && document.getData() != null) {
            return ResponseEntity.ok()
                    .header("Content-Disposition",
                            "attachment; filename=\"" + document.getDocName() + ".pdf\"")
                    .body(document.getData());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ===============================
    // VIEW DOCUMENT BY NAME
    // ===============================
    @GetMapping(value = "/view/name/{name}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> viewDocumentByName(@PathVariable String name) {

        Document document = documentService.getDocumentByName(name);

        if (document != null && document.getData() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition",
                            "inline; filename=\"" + document.getDocName() + ".pdf\"")
                    .body(document.getData());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ===============================
    // VIEW DOCUMENT BY UPLOAD DATE
    // ===============================
    @GetMapping(value = "/view/date/{date}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> viewDocumentByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Document document = documentService.getDocumentByDate(date);

        if (document != null && document.getData() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition",
                            "inline; filename=\"" + document.getDocName() + ".pdf\"")
                    .body(document.getData());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ===============================
    // VIEW ALL DOCUMENT METADATA
    // ===============================
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {

        List<Document> documents = documentService.getDocuments();

        if (!documents.isEmpty()) {
            return new ResponseEntity<>(documents, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ===============================
    // UPDATE DOCUMENT BY ID
    // ===============================
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Document> updateDocumentById(
            @PathVariable Long id,
            @RequestParam("docName") String docName,
            @RequestParam("uploadDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate uploadDate,
            @RequestParam("file") MultipartFile file) {

        try {
            Document document = new Document();
            document.setDocName(docName);
            document.setUploadDate(uploadDate);
            document.setData(file.getBytes());

            Document updated = documentService.updateDocumentById(id, document);

            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // ===============================
    // DELETE DOCUMENT BY ID
    // ===============================
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteDocumentById(@PathVariable Long id) {

        boolean deleted = documentService.deleteDocumentById(id);

        if (deleted) {
            return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Document not found", HttpStatus.NOT_FOUND);
    }

    // ===============================
    // DELETE DOCUMENT BY NAME
    // ===============================
    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteDocumentByName(@PathVariable String name) {

        boolean deleted = documentService.deleteDocumentByName(name);

        if (deleted) {
            return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Document not found", HttpStatus.NOT_FOUND);
    }


    // ===============================
    // DELETE ALL DOCUMENTS
    // ===============================
    @DeleteMapping
    public ResponseEntity<String> deleteAllDocuments() {

        boolean deleted = documentService.deleteDocuments();

        if (deleted) {
            return new ResponseEntity<>("All documents deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>("No documents available", HttpStatus.NO_CONTENT);
    }
        // ---------- PAGINATION ----------
    @GetMapping("/pagination")
    public ResponseEntity<Page<Document>> pagination(
            @RequestParam int page,
            @RequestParam int size) {

        return ResponseEntity.ok(
                documentService.getDocumentsWithPagination(page, size));
    }

    // ---------- SORTING ----------
    @GetMapping("/sorting")
    public ResponseEntity<List<Document>> sorting(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                documentService.getDocumentsSorted(field, direction));
    }

    // ---------- PAGINATION + SORTING ----------
    @GetMapping("/pagination-sorting")
    public ResponseEntity<Page<Document>> paginationSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                documentService.getDocumentsWithPaginationAndSorting(
                        page, size, field, direction));
    }

    // ---------- SORT BY ENTITY ----------
    @GetMapping("/sort/docId")
    public ResponseEntity<List<Document>> sortByDocId(
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(documentService.sortByDocId(direction));
    }

    @GetMapping("/sort/docName")
    public ResponseEntity<List<Document>> sortByDocName(
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(documentService.sortByDocName(direction));
    }

    @GetMapping("/sort/uploadDate")
    public ResponseEntity<List<Document>> sortByUploadDate(
            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(documentService.sortByUploadDate(direction));
    }

}
