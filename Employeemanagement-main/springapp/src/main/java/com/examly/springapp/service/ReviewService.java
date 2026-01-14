package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.examly.springapp.model.Review;

public interface ReviewService {

    Review saveReview(Review review);

    List<Review> saveReviews(List<Review> reviews);

    List<Review> getAllReviews();

    Review getReviewById(Long id);

    Review getReviewByRating(int rating);

    Review getReviewByDate(LocalDate localDate);

    Review updatReview(Review review);

    boolean deleteReviews();

    boolean deleteReviewById(Long id);

    // Pagination only
    Page<Review> getReviewsWithPagination(int page, int size);

    // Sorting only
    List<Review> getReviewsWithSorting(String field, String direction);

    // Pagination + Sorting
    Page<Review> getReviewsWithPaginationAndSorting(
            int page, int size, String field, String direction);

    // Sorting by specific fields
    List<Review> sortByRating();
    List<Review> sortByReviewDate();
    List<Review> sortByReviewerId();
}