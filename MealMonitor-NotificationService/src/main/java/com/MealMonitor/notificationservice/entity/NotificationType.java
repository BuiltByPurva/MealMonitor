package com.MealMonitor.notificationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notification_types")
public class NotificationType {
    @Id
    @Column(name = "notification_type_id", length = 50)
    private String notificationTypeId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "type_name")
    private String typeName;

    // Constructors
    public NotificationType() {}

    public NotificationType(String notificationTypeId, String typeName) {
        this.notificationTypeId = notificationTypeId;
        this.typeName = typeName;
    }

    // Getters and Setters
    public String getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(String notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
