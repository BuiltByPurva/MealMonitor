package com.MealMonitor.reviewservice.service;

import com.MealMonitor.reviewservice.entity.Review;
import com.MealMonitor.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByDishId(String dishId) {
        return reviewRepository.findByDishId(dishId);
    }

    public List<Review> getReviewsByUserId(String userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getRecentReviews() {
        return reviewRepository.findAll().stream()
                .sorted((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()))
                .limit(10)
                .toList();
    }

    public Review getReviewById(String id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(String id, Review reviewDetails) {
        Review review = getReviewById(id);
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        review.setIsAnonymous(reviewDetails.getIsAnonymous());
        review.setUpdatedAt(java.time.LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(String id) {
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }
}
