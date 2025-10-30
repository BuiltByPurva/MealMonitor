package com.MealMonitor.canteenservice.controller;

import com.MealMonitor.canteenservice.entity.CanteenItem;
import com.MealMonitor.canteenservice.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/canteen")
//@CrossOrigin(origins = "http://localhost:3000")
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @GetMapping("/items")
    public ResponseEntity<List<CanteenItem>> getAllItems() {
        return ResponseEntity.ok(canteenService.getAllItems());
    }

    @GetMapping("/items/category/{category}")
    public ResponseEntity<List<CanteenItem>> getItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(canteenService.getItemsByCategory(category));
    }

    @GetMapping("/items/available")
    public ResponseEntity<List<CanteenItem>> getAvailableItems() {
        return ResponseEntity.ok(canteenService.getAvailableItems());
    }

    @GetMapping("/items/search")
    public ResponseEntity<List<CanteenItem>> searchItems(@RequestParam String name) {
        return ResponseEntity.ok(canteenService.searchItems(name));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<?> getItemById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(canteenService.getItemById(id));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<?> createItem(@Valid @RequestBody CanteenItem item) {
        try {
            return ResponseEntity.ok(canteenService.createItem(item));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to create item: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable String id, @Valid @RequestBody CanteenItem itemDetails) {
        try {
            return ResponseEntity.ok(canteenService.updateItem(id, itemDetails));
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable String id) {
        try {
            canteenService.deleteItem(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Item deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
