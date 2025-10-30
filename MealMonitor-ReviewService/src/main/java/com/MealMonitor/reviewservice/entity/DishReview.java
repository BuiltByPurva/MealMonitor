package com.MealMonitor.reviewservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "dish_review")
public class DishReview {
    @Id
    @Column(name = "dish_id", length = 50)
    private String dishId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "dish_name")
    private String dishName;

    @Size(max = 1000)
    private String description;

    @Size(max = 500)
    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public DishReview() {}

    public DishReview(String dishId, String dishName, String description) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.description = description;
    }

    // Getters and Setters
    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
