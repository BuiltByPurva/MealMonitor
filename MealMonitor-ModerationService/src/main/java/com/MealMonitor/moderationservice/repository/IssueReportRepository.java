package com.MealMonitor.moderationservice.repository;

import com.MealMonitor.moderationservice.entity.IssueReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueReportRepository extends JpaRepository<IssueReport, String> {
    List<IssueReport> findByStatus(String status);
}


