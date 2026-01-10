package com.examly.springapp.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DailyPayroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dpayrollId;
    private double dbasicSalary;
    private double ddeductions;
    private double dnetSalary;
    private Date ddate;
    public Long getdpayrollId() {
        return dpayrollId;
    }
    public void setdpayrollId(Long dpayrollId) {
        this.dpayrollId = dpayrollId;
    }
    public double getdbasicSalary() {
        return dbasicSalary;
    }
    public void setdbasicSalary(double dbasicSalary) {
        this.dbasicSalary = dbasicSalary;
    }
    public double getddeductions() {
        return ddeductions;
    }
    public void setddeductions(double ddeductions) {
        this.ddeductions = ddeductions;
    }
    public double getdnetSalary() {
        return dnetSalary;
    }
    public void setdnetSalary(double dnetSalary) {
        this.dnetSalary = dnetSalary;
    }
    public Date getddate() {
        return ddate;
    }
    public void setddate(Date ddate) {
        this.ddate = ddate;
    }
    public DailyPayroll(double dbasicSalary, double ddeductions, double dnetSalary, Date ddate) {
        this.dbasicSalary = dbasicSalary;
        this.ddeductions = ddeductions;
        this.dnetSalary = dnetSalary;
        this.ddate = ddate;
    }
    public DailyPayroll() {
    }  
}
