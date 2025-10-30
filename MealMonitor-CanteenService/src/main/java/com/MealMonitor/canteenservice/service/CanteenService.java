package com.MealMonitor.canteenservice.service;

import com.MealMonitor.canteenservice.entity.AvailabilityStatus;
import com.MealMonitor.canteenservice.entity.CanteenItem;
import com.MealMonitor.canteenservice.repository.CanteenItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanteenService {

    @Autowired
    private CanteenItemRepository canteenItemRepository;

    public List<CanteenItem> getAllItems() {
        return canteenItemRepository.findAll();
    }

    public List<CanteenItem> getItemsByCategory(String category) {
        return canteenItemRepository.findByCategory(category);
    }

    public List<CanteenItem> getAvailableItems() {
        return canteenItemRepository.findByAvailabilityStatus(AvailabilityStatus.AVAILABLE);
    }

    public List<CanteenItem> searchItems(String dishName) {
        return canteenItemRepository.findByDishNameContainingIgnoreCase(dishName);
    }

    public CanteenItem getItemById(String id) {
        return canteenItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canteen item not found with id: " + id));
    }

    public CanteenItem createItem(CanteenItem item) {
        return canteenItemRepository.save(item);
    }

    public CanteenItem updateItem(String id, CanteenItem itemDetails) {
        CanteenItem item = getItemById(id);
        item.setDishName(itemDetails.getDishName());
        item.setDescription(itemDetails.getDescription());
        item.setPrice(itemDetails.getPrice());
        item.setCategory(itemDetails.getCategory());
        item.setAvailabilityStatus(itemDetails.getAvailabilityStatus());
        item.setImageURL(itemDetails.getImageURL());
        item.setUpdatedAt(java.time.LocalDateTime.now());
        return canteenItemRepository.save(item);
    }

    public void deleteItem(String id) {
        CanteenItem item = getItemById(id);
        canteenItemRepository.delete(item);
    }
}
