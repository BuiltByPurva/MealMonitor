package com.MealMonitor.moderationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "resolution_updates")
public class ResolutionUpdate {
    @Id
    @Column(name = "resolution_id", length = 50)
    private String resolutionId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "issue_id")
    private String issueId;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "resolution_text")
    private String resolutionText;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt = LocalDateTime.now();

    @NotBlank
    @Size(max = 50)
    @Column(name = "resolved_by_user_id")
    private String resolvedByUserId;

    // Constructors
    public ResolutionUpdate() {}

    public ResolutionUpdate(String resolutionId, String issueId, String resolutionText, String resolvedByUserId) {
        this.resolutionId = resolutionId;
        this.issueId = issueId;
        this.resolutionText = resolutionText;
        this.resolvedByUserId = resolvedByUserId;
    }

    // Getters and Setters
    public String getResolutionId() {
        return resolutionId;
    }

    public void setResolutionId(String resolutionId) {
        this.resolutionId = resolutionId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getResolutionText() {
        return resolutionText;
    }

    public void setResolutionText(String resolutionText) {
        this.resolutionText = resolutionText;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public String getResolvedByUserId() {
        return resolvedByUserId;
    }

    public void setResolvedByUserId(String resolvedByUserId) {
        this.resolvedByUserId = resolvedByUserId;
    }
}
