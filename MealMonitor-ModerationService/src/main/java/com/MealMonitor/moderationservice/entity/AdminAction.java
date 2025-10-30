package com.MealMonitor.moderationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_actions")
public class AdminAction {
    @Id
    @Column(name = "action_id", length = 50)
    private String actionId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "admin_id")
    private String adminId;

    @NotBlank
    @Size(max = 1000)
    @Column(name = "action_description")
    private String actionDescription;

    @Column(name = "performed_at")
    private LocalDateTime performedAt = LocalDateTime.now();

    // Constructors
    public AdminAction() {}

    public AdminAction(String actionId, String adminId, String actionDescription) {
        this.actionId = actionId;
        this.adminId = adminId;
        this.actionDescription = actionDescription;
    }

    // Getters and Setters
    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(LocalDateTime performedAt) {
        this.performedAt = performedAt;
    }
}
