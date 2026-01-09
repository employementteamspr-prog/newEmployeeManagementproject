package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Document;
import com.examly.springapp.service.DocumentService;

@RestController
@RequestMapping("/api")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @PostMapping("/document")
    public ResponseEntity<Document> saveDocument(Document document){
        Document saved=documentService.saveDocument(document);
        if(saved!=null){
            return new ResponseEntity<>(saved,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/documents")
    public ResponseEntity<List<Document>> saveDocuments(List<Document> document){
        List<Document> saveddocs=documentService.saveDocuments(document);
        if(saveddocs!=null){
            return new ResponseEntity<>(saveddocs,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getAllDocuments(){
        List<Document> documents=documentService.getDocuments();
        if(documents!=null){
            return new ResponseEntity<>(documents,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("document/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id){
        Document document=documentService.getDocumentById(id);
        if(document!=null){
            return new ResponseEntity<>(document,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
