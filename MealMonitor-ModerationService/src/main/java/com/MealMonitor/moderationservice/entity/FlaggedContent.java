package com.MealMonitor.moderationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "flagged_content")
public class FlaggedContent {
    @Id
    @Column(name = "flag_id", length = 50)
    private String flagId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "flagged_by_user_id")
    private String flaggedByUserId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "content_type")
    private String contentType;

    @NotBlank
    @Size(max = 50)
    @Column(name = "content_id")
    private String contentId;

    @NotBlank
    @Size(max = 500)
    @Column(name = "reason")
    private String reason;

    @Column(name = "flagged_at")
    private LocalDateTime flaggedAt = LocalDateTime.now();

    @NotBlank
    @Size(max = 50)
    @Column(name = "status")
    private String status = "PENDING";

    // Constructors
    public FlaggedContent() {}

    public FlaggedContent(String flagId, String flaggedByUserId, String contentType, String contentId, String reason) {
        this.flagId = flagId;
        this.flaggedByUserId = flaggedByUserId;
        this.contentType = contentType;
        this.contentId = contentId;
        this.reason = reason;
    }

    // Getters and Setters
    public String getFlagId() {
        return flagId;
    }

    public void setFlagId(String flagId) {
        this.flagId = flagId;
    }

    public String getFlaggedByUserId() {
        return flaggedByUserId;
    }

    public void setFlaggedByUserId(String flaggedByUserId) {
        this.flaggedByUserId = flaggedByUserId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getFlaggedAt() {
        return flaggedAt;
    }

    public void setFlaggedAt(LocalDateTime flaggedAt) {
        this.flaggedAt = flaggedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
