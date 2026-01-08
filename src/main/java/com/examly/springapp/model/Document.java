package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    private int docId;
    private String docName;
    private String uploadDate;

    
    public Document() {
    }


    public Document(int docId, String docName, String uploadDate) {
        this.docId = docId;
        this.docName = docName;
        this.uploadDate = uploadDate;
    }


    public int getDocId() {
        return docId;
    }


    public void setDocId(int docId) {
        this.docId = docId;
    }


    public String getDocName() {
        return docName;
    }


    public void setDocName(String docName) {
        this.docName = docName;
    }


    public String getUploadDate() {
        return uploadDate;
    }


    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    
}
