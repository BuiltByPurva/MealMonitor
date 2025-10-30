package com.MealMonitor.notificationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_notifications")
public class UserNotification {
    @Id
    @Column(name = "user_notification_id", length = 50)
    private String userNotificationId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_id")
    private String userId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "notification_id")
    private String notificationId;

    @Column(name = "is_read")
    private Boolean isRead = false;

    @Column(name = "sent_at")
    private LocalDateTime sentAt = LocalDateTime.now();

    // Constructors
    public UserNotification() {}

    public UserNotification(String userNotificationId, String userId, String notificationId) {
        this.userNotificationId = userNotificationId;
        this.userId = userId;
        this.notificationId = notificationId;
    }

    // Getters and Setters
    public String getUserNotificationId() {
        return userNotificationId;
    }

    public void setUserNotificationId(String userNotificationId) {
        this.userNotificationId = userNotificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
