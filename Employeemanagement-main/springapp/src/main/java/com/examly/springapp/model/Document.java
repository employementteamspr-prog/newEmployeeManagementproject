package com.examly.springapp.model;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Document {
    @Id
    private Long docId;
    private String docName;
    private LocalDate uploadDate;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
    
    public Document() {
    }

    public Document(Long docId, String docName, LocalDate uploadDate, byte[] data) {
        this.docId = docId;
        this.docName = docName;
        this.uploadDate = uploadDate;
        this.data = data;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
    
}
