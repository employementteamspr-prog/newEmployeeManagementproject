package com.examly.springapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Review;
import com.examly.springapp.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewController {
    
    @Autowired
    ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Review> saveReview(@RequestBody Review review){
           Review saved=reviewService.saveReview(review);

           if(saved!=null){
               return new ResponseEntity<>(saved,HttpStatus.CREATED);
           }
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/reviews")
    public ResponseEntity<List<Review>> saveReviews(@RequestBody List<Review> reviews){
        List<Review> savedReviews=reviewService.saveReviews(reviews);

        if(savedReviews!=null){
            return new ResponseEntity<>(savedReviews,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews=reviewService.getAllReviews();

        if(reviews!=null){
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     // Get review by ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Get review by rating
    @GetMapping("/rating/{rating}")
    public ResponseEntity<Review> getReviewByRating(@PathVariable int rating) {
        Review review = reviewService.getReviewByRating(rating);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Get review by date
    @GetMapping("/review/date/{date}")
    public ResponseEntity<Review> getReviewByDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Review review = reviewService.getReviewByDate(date);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Update review
    @PutMapping("/review")
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
        Review updated = reviewService.updatReview(review);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    // Delete review by ID
    @DeleteMapping("/review/{id}")
    public ResponseEntity<Boolean> deleteReviewById(@PathVariable Long id) {
        boolean deleted = reviewService.deleteReviewById(id);

        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    // Delete all reviews
    @DeleteMapping("/review")
    public ResponseEntity<Boolean> deleteReviews() {
        boolean deleted = reviewService.deleteReviews();

        if (deleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }
    // ===============================
    // PAGINATION ONLY
    // ===============================
    @GetMapping("/page")
    public ResponseEntity<Page<Review>> getReviewsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        return new ResponseEntity<>(
                reviewService.getReviewsWithPagination(page, size),
                HttpStatus.OK
        );
    }

    // ===============================
    // SORTING ONLY
    // ===============================
    @GetMapping("/sort")
    public ResponseEntity<List<Review>> getReviewsWithSorting(
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return new ResponseEntity<>(
                reviewService.getReviewsWithSorting(field, direction),
                HttpStatus.OK
        );
    }

    // ===============================
    // PAGINATION + SORTING
    // ===============================
    @GetMapping("/page-sort")
    public ResponseEntity<Page<Review>> getReviewsWithPaginationAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field,
            @RequestParam(defaultValue = "asc") String direction) {

        return new ResponseEntity<>(
                reviewService.getReviewsWithPaginationAndSorting(
                        page, size, field, direction),
                HttpStatus.OK
        );
    }

    // ===============================
    // SORT BY SPECIFIC FIELDS
    // ===============================
    @GetMapping("/sort/rating")
    public List<Review> sortByRating() {
        return reviewService.sortByRating();
    }

    @GetMapping("/sort/date")
    public List<Review> sortByDate() {
        return reviewService.sortByReviewDate();
    }

    @GetMapping("/sort/reviewer")
    public List<Review> sortByReviewer() {
        return reviewService.sortByReviewerId();
    }
}