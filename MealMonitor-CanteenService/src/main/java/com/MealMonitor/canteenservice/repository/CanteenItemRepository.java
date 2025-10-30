package com.MealMonitor.canteenservice.repository;

import com.MealMonitor.canteenservice.entity.AvailabilityStatus;
import com.MealMonitor.canteenservice.entity.CanteenItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanteenItemRepository extends JpaRepository<CanteenItem, String> {
    List<CanteenItem> findByCategory(String category);
    List<CanteenItem> findByAvailabilityStatus(AvailabilityStatus availabilityStatus);
    List<CanteenItem> findByDishNameContainingIgnoreCase(String dishName);
}
