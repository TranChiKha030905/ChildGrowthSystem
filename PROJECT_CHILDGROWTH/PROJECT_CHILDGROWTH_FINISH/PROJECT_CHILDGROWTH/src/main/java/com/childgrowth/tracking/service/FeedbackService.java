package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.Feedback;
import com.childgrowth.tracking.model.User;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedback();
    List<Feedback> getFeedbackByUser(User user);
    List<Feedback> getAllPublicFeedback() ;
    List<Feedback> getFeedbackByType(String type);
    List<Feedback> getFeedbackByStatus(String status);
    List<Feedback> getFeedbackByPriority(String priority);
    List<Feedback> getFeedbackByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Feedback> getPublicFeedback();
    void updateFeedback(Feedback feedback);
    void deleteFeedback(Long id);
    void markFeedbackAsResolved(Long id);
    void addResponse(Long id, String response, User responder);
    double calculateAverageRating();

    Page<Feedback> findAll(Pageable pageable);
    Page<Feedback> findByStatus(String status, Pageable pageable);
    Page<Feedback> findByType(String type, Pageable pageable);
    Page<Feedback> findByStatusAndType(String status, String type, Pageable pageable);
    Optional<Feedback> findById(Long id);
    void respondToFeedback(Long id, String response, String newStatus, User respondedBy);
    void updateFeedbackStatus(Long id, String status, User updatedBy);
    void saveFeedback(Feedback feedback);
}