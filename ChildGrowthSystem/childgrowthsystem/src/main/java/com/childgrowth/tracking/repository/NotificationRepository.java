package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.Notification;
import com.childgrowth.tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
    List<Notification> findByUserAndIsRead(User user, boolean isRead);
    List<Notification> findByUserAndType(User user, String type);
    List<Notification> findByUserAndPriority(User user, String priority);
    List<Notification> findByUserAndCategory(User user, String category);
    List<Notification> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Notification> findByUserAndIsArchived(User user, boolean isArchived);
} 