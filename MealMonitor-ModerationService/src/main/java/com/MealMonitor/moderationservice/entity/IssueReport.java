package com.MealMonitor.moderationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "issue_reports")
public class IssueReport {
    @Id
    @Column(name = "issue_id", length = 50)
    private String issueId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "dish_id")
    private String dishId;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "issue_description")
    private String issueDescription;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt = LocalDateTime.now();

    @NotBlank
    @Size(max = 50)
    @Column(name = "status")
    private String status = "PENDING";

    @NotBlank
    @Size(max = 50)
    @Column(name = "reported_by_user_id")
    private String reportedByUserId;

    // Constructors
    public IssueReport() {}

    public IssueReport(String issueId, String dishId, String issueDescription, String reportedByUserId) {
        this.issueId = issueId;
        this.dishId = dishId;
        this.issueDescription = issueDescription;
        this.reportedByUserId = reportedByUserId;
    }

    // Getters and Setters
    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportedByUserId() {
        return reportedByUserId;
    }

    public void setReportedByUserId(String reportedByUserId) {
        this.reportedByUserId = reportedByUserId;
    }
}
