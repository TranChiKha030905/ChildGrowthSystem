package com.childgrowth.tracking.service;

import com.childgrowth.tracking.model.Feedback;
import com.childgrowth.tracking.model.User;
import java.time.LocalDateTime;
import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback getFeedbackById(Long id);
    List<Feedback> getAllFeedback();
    List<Feedback> getFeedbackByUser(User user);
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
} 