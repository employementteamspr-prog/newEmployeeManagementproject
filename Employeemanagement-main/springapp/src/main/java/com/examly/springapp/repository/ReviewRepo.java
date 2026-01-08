package com.examly.springapp.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Review;

public interface ReviewRepo extends JpaRepository<Review,Integer> {
    Review findByRating(int rating);

    Review findByReviewDate(LocalDate reviewDate);

    Optional<Review> findById(Long id);

    void deleteById(Long id);
}
