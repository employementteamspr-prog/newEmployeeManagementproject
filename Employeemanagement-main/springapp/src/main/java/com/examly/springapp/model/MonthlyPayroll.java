package com.examly.springapp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class MonthlyPayroll {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private int monthlyPayrollId;//primary key
    private double mBasicSalary;
    private double mNetSalary;
    private double mDeductions;
    private int month;//1-12

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="employeeId",nullable=false)
    private Employee employee;

    @OneToMany(mappedBy="monthlyPayrollId",fetch=FetchType.LAZY)
    private List<Leave> leaves;

    @OneToMany(mappedBy = "monthlyPayroll", fetch = FetchType.LAZY)
    private List<Attendance> attendances;


    //Empty Constructor
    public MonthlyPayroll() {
    }
    //Parameterized Constructor

    public MonthlyPayroll(double mBasicSalary, double mNetSalary,
            double mDeductions, int month) {
        this.mBasicSalary = mBasicSalary;
        this.mNetSalary = mNetSalary;
        this.mDeductions = mDeductions;
        this.month = month;
    }
    //getters and setters

    public int getMonthlyPayrollId() {
        return monthlyPayrollId;
    }

    public void setMonthlyPayrollId(int monthlyPayrollId) {
        this.monthlyPayrollId = monthlyPayrollId;
    }


    public double getmBasicSalary() {
        return mBasicSalary;
    }

    public void setmBasicSalary(double mBasicSalary) {
        this.mBasicSalary = mBasicSalary;
    }

    public double getmNetSalary() {
        return mNetSalary;
    }

    public void setmNetSalary(double mNetSalary) {
        this.mNetSalary = mNetSalary;
    }

    public double getmDeductions() {
        return mDeductions;
    }

    public void setmDeductions(double mDeductions) {
        this.mDeductions = mDeductions;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
}