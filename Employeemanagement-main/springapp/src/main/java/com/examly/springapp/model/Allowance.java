package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Allowance {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    private int allowanceId ;//primary key
    private String allowanceType ;
    private double amount ;
    private String frequency;


    //Empty ConstructorParameterized Constructor
    public Allowance() {
    }
    //Parameterized Constructor
    public Allowance(String allowanceType, double amount, String frequency) {
        this.allowanceType = allowanceType;
        this.amount = amount;
        this.frequency = frequency;
    }
    //getters and setters
    public int getAllowanceId() {
        return allowanceId;
    }
    public void setAllowanceId(int allowanceId) {
        this.allowanceId = allowanceId;
    }
    public String getAllowanceType() {
        return allowanceType;
    }
    public void setAllowanceType(String allowanceType) {
        this.allowanceType = allowanceType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    
}