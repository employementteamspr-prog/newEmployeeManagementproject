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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private Employee employee;
    
    public Document() {
    }

    public Document(Long docId, String docName, LocalDate uploadDate, byte[] data, Employee employee) {
        this.docId = docId;
        this.docName = docName;
        this.uploadDate = uploadDate;
        this.data = data;
        this.employee = employee;
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
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
}

