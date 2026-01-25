
package com.examly.springapp.model;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    private Long reviewId;
    private Long reviewerId;
    private String comments;
    private int rating;
    private LocalDate reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewerId")
    private Employee reviewer;
    
    public Review() {
    }

    public Review(Long reviewId, Long reviewerId, String comments, int rating, LocalDate reviewDate, Employee employee, Employee reviewer) {
        this.reviewId = reviewId;
        this.reviewerId = reviewerId;
        this.comments = comments;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.employee = employee;
        this.reviewer = reviewer;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
    public Employee getEmployee() {
        return employee;
    }       
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }   
    public Employee getReviewer() {
        return reviewer;
    }
    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }
}

