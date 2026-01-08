package com.examly.springapp.model;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    private Long reviewId;
    private Long reviewerId;
    private String comments;
    private int review;
    private LocalDate reviewDate;

    
    public Review() {
    }

    public Review(Long reviewId, Long reviewerId, String comments, int review, LocalDate reviewDate) {
        this.reviewId = reviewId;
        this.reviewerId = reviewerId;
        this.comments = comments;
        this.review = review;
        this.reviewDate = reviewDate;
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

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

}
