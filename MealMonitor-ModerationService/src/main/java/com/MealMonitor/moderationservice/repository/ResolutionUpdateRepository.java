package com.MealMonitor.moderationservice.repository;

import com.MealMonitor.moderationservice.entity.ResolutionUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResolutionUpdateRepository extends JpaRepository<ResolutionUpdate, String> {
    List<ResolutionUpdate> findByIssueId(String issueId);
}


