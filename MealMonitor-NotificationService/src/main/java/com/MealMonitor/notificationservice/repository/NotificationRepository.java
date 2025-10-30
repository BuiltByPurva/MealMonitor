package com.MealMonitor.notificationservice.repository;

import com.MealMonitor.notificationservice.entity.Notification;
import com.MealMonitor.notificationservice.entity.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByUserId(String userId);
    List<Notification> findByStatus(NotificationStatus status);
    List<Notification> findByUserIdAndStatus(String userId, NotificationStatus status);
    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);
}
