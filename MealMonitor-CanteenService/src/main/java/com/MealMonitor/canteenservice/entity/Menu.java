package com.MealMonitor.canteenservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @Column(name = "menu_id", length = 50)
    private String menuId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "canteen_name")
    private String canteenName;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructors
    public Menu() {}

    public Menu(String menuId, String canteenName) {
        this.menuId = menuId;
        this.canteenName = canteenName;
    }

    // Getters and Setters
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
