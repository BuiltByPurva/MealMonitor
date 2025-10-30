package com.MealMonitor.moderationservice.repository;

import com.MealMonitor.moderationservice.entity.ModerationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModerationLogRepository extends JpaRepository<ModerationLog, String> {
}


