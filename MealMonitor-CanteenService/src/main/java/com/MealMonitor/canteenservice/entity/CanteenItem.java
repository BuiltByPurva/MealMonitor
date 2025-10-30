package com.MealMonitor.canteenservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "dish_canteen")
public class CanteenItem {

    @Id
    @Column(name = "dish_id", length = 50)
    private String dishId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "menu_id")
    private String menuId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "dish_name")
    private String dishName;

    @Size(max = 500)
    @Column(name = "description")
    private String description;

    @Size(max = 100)
    @Column(name = "category")
    private String category;

    @NotNull
    @Positive
    @Column(name = "price")
    private Double price;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status")
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

    @Size(max = 500)
    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public CanteenItem() {}

    public CanteenItem(String dishId, String menuId, String dishName, String description, String category,
                       Double price, Boolean isAvailable, AvailabilityStatus availabilityStatus, String imageURL) {
        this.dishId = dishId;
        this.menuId = menuId;
        this.dishName = dishName;
        this.description = description;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
        this.availabilityStatus = availabilityStatus;
        this.imageURL = imageURL;
    }

    // Getters & Setters
    public String getDishId() { return dishId; }
    public void setDishId(String dishId) { this.dishId = dishId; }

    public String getMenuId() { return menuId; }
    public void setMenuId(String menuId) { this.menuId = menuId; }

    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }

    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
