package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.model.Notification;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.NotificationRepository;
import com.childgrowth.tracking.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public Notification createNotification(Notification notification) {
        validateNotificationData(notification);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    @Override
    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByUser(user);
    }

    @Override
    public List<Notification> getUnreadNotificationsByUser(User user) {
        return notificationRepository.findByUserAndIsRead(user, false);
    }

    @Override
    public List<Notification> getNotificationsByType(User user, String type) {
        return notificationRepository.findByUserAndType(user, type);
    }

    @Override
    public List<Notification> getNotificationsByPriority(User user, String priority) {
        return notificationRepository.findByUserAndPriority(user, priority);
    }

    @Override
    public List<Notification> getNotificationsByCategory(User user, String category) {
        return notificationRepository.findByUserAndCategory(user, category);
    }

    @Override
    public List<Notification> getNotificationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return notificationRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    @Transactional
    public void markNotificationAsRead(Long id) {
        Notification notification = getNotificationById(id);
        notification.setRead(true);
        notification.setReadAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void markAllNotificationsAsRead(User user) {
        List<Notification> unreadNotifications = getUnreadNotificationsByUser(user);
        LocalDateTime now = LocalDateTime.now();
        for (Notification notification : unreadNotifications) {
            notification.setRead(true);
            notification.setReadAt(now);
        }
        notificationRepository.saveAll(unreadNotifications);
    }

    @Override
    @Transactional
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found with id: " + id);
        }
        notificationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void sendNotification(Notification notification) {
        notification = createNotification(notification);
        sendEmailNotification(notification);
        sendPushNotification(notification);
    }

    @Override
    public void sendEmailNotification(Notification notification) {
        // TODO: Implement email sending logic
        notification.setEmailSent(true);
        notification.setEmailSentAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    public void sendPushNotification(Notification notification) {
        // TODO: Implement push notification logic
        notification.setPushSent(true);
        notification.setPushSentAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    private void validateNotificationData(Notification notification) {
        if (notification.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (notification.getType() == null || notification.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("Notification type cannot be empty");
        }
        if (notification.getTitle() == null || notification.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (notification.getMessage() == null || notification.getMessage().trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        if (notification.getPriority() == null || notification.getPriority().trim().isEmpty()) {
            throw new IllegalArgumentException("Priority cannot be empty");
        }
    }
} 