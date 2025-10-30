package com.MealMonitor.reviewservice.repository;

import com.MealMonitor.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByDishId(String dishId);
    List<Review> findByUserId(String userId);
    List<Review> findByRating(Integer rating);
    List<Review> findByCommentContainingIgnoreCase(String text);
}
