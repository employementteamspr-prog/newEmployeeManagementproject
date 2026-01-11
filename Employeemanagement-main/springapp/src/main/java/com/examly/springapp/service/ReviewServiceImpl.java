package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Review;
import com.examly.springapp.repository.ReviewRepo;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepository;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> saveReviews(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

    @Override
    public Review getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            return review.get();
        } else {
            return null;
        }
    }

    @Override
    public Review getReviewByRating(int rating) {
        Review review = reviewRepository.findByRating(rating);

        if (review != null) {
            return review;
        } else {
            return null;
        }
    }

    @Override
    public Review getReviewByDate(LocalDate localDate) {
        Review review = reviewRepository.findByReviewDate(localDate);

        if (review != null) {
            return review;
        } else {
            return null;
        }
    }

    @Override
    public Review updatReview(Review review) {
        if (review != null && review.getReviewId() != null) {
            return reviewRepository.save(review);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteReviews() {
        List<Review> reviews = reviewRepository.findAll();

        if (reviews != null && !reviews.isEmpty()) {
            reviewRepository.deleteAll();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            reviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
