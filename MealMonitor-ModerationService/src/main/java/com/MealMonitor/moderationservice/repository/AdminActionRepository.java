package com.MealMonitor.moderationservice.repository;

import com.MealMonitor.moderationservice.entity.AdminAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminActionRepository extends JpaRepository<AdminAction, String> {
}


