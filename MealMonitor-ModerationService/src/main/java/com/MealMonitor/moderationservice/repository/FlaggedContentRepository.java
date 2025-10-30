package com.MealMonitor.moderationservice.repository;

import com.MealMonitor.moderationservice.entity.FlaggedContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlaggedContentRepository extends JpaRepository<FlaggedContent, String> {
    List<FlaggedContent> findByStatus(String status);
}


