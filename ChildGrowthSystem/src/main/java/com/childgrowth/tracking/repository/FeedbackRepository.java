package com.childgrowth.tracking.repository;

import com.childgrowth.tracking.model.Feedback;
import com.childgrowth.tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUser(User user);
    List<Feedback> findByType(String type);
    List<Feedback> findByStatus(String status);
    List<Feedback> findByPriority(String priority);
    List<Feedback> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Feedback> findByIsPublic(boolean isPublic);
    List<Feedback> findByOverallRatingGreaterThanEqual(Integer rating);
} 