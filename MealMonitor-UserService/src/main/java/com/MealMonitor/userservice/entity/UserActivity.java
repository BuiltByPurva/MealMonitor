package com.MealMonitor.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity")
public class UserActivity {
    @Id
    @Column(name = "activity_id", length = 50)
    private String activityId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_id")
    private String userId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "activity_type")
    private String activityType;

    @NotBlank
    @Size(max = 100)
    @Column(name = "entity_type")
    private String entityType;

    @NotBlank
    @Size(max = 50)
    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Constructors
    public UserActivity() {}

    public UserActivity(String activityId, String userId, String activityType, String entityType, String entityId) {
        this.activityId = activityId;
        this.userId = userId;
        this.activityType = activityType;
        this.entityType = entityType;
        this.entityId = entityId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
