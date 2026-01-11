package com.examly.springapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    // ===============================
// PAGINATION + SORTING
// ===============================
@GetMapping("/paginated")
public ResponseEntity<Page<Document>> getDocumentsPaginated(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "uploadDate") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    Page<Document> documents =
            documentService.getDocumentsPaginated(page, size, sortBy, direction);

    if (!documents.isEmpty()) {
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}
