package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.Notification;
import com.childgrowth.tracking.model.User;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Notification getNotificationById(Long id);
    List<Notification> getNotificationsByUser(User user);
    List<Notification> getUnreadNotificationsByUser(User user);
    List<Notification> getNotificationsByType(User user, String type);
    List<Notification> getNotificationsByPriority(User user, String priority);
    List<Notification> getNotificationsByCategory(User user, String category);
    List<Notification> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    void markNotificationAsRead(Long id);
    void markAllNotificationsAsRead(User user);
    void deleteNotification(Long id);
    void sendNotification(Notification notification);
    void sendEmailNotification(Notification notification);
    void sendPushNotification(Notification notification);
} 