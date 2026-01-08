package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import com.examly.springapp.model.Review;

public interface ReviewService {

    Review saveReview(Review review);

    List<Review> saveReviews(List<Review> reviews);

    Review getReviewById(Long id);

    Review getReviewByRating(int rating);

    Review getReviewByDate(LocalDate localDate);

    Review updatReview(Review review);

    boolean deleteReviews();

    boolean deleteReviewById(Long id);
}