package com.MealMonitor.moderationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "moderation_logs")
public class ModerationLog {
    @Id
    @Column(name = "log_id", length = 50)
    private String logId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "admin_id")
    private String adminId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "action_type")
    private String actionType;

    @NotBlank
    @Size(max = 100)
    @Column(name = "target_entity")
    private String targetEntity;

    @NotBlank
    @Size(max = 50)
    @Column(name = "target_id")
    private String targetId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();

    // Constructors
    public ModerationLog() {}

    public ModerationLog(String logId, String adminId, String actionType, String targetEntity, String targetId) {
        this.logId = logId;
        this.adminId = adminId;
        this.actionType = actionType;
        this.targetEntity = targetEntity;
        this.targetId = targetId;
    }

    // Getters and Setters
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
