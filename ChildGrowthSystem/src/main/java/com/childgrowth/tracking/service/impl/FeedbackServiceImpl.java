package com.childgrowth.tracking.service.impl;

import com.childgrowth.tracking.exception.FeedbackNotFoundException;
import com.childgrowth.tracking.model.Feedback;
import com.childgrowth.tracking.model.User;
import com.childgrowth.tracking.repository.FeedbackRepository;
import com.childgrowth.tracking.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public Feedback createFeedback(Feedback feedback) {
        validateFeedbackData(feedback);
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setStatus("PENDING");
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID phản hồi không được để trống");
        }
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Không tìm thấy phản hồi với id: " + id));
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getFeedbackByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Người dùng không được để trống");
        }
        return feedbackRepository.findByUser(user);
    }

    @Override
    public List<Feedback> getFeedbackByType(String type) {
        if (!StringUtils.hasText(type)) {
            throw new IllegalArgumentException("Loại phản hồi không được để trống");
        }
        return feedbackRepository.findByType(type);
    }

    @Override
    public List<Feedback> getFeedbackByStatus(String status) {
        if (!StringUtils.hasText(status)) {
            throw new IllegalArgumentException("Trạng thái không được để trống");
        }
        return feedbackRepository.findByStatus(status);
    }

    @Override
    public List<Feedback> getFeedbackByPriority(String priority) {
        if (!StringUtils.hasText(priority)) {
            throw new IllegalArgumentException("Độ ưu tiên không được để trống");
        }
        return feedbackRepository.findByPriority(priority);
    }

    @Override
    public List<Feedback> getFeedbackByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Ngày bắt đầu và kết thúc không được để trống");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu không thể sau ngày kết thúc");
        }
        return feedbackRepository.findByCreatedAtBetween(startDate, endDate);
    }

    @Override
    public List<Feedback> getPublicFeedback() {
        return feedbackRepository.findByIsPublic(true);
    }

    @Override
    @Transactional
    public void updateFeedback(Feedback feedback) {
        validateFeedbackData(feedback);
        if (feedback.getId() == null) {
            throw new IllegalArgumentException("ID phản hồi không được để trống");
        }
        if (!feedbackRepository.existsById(feedback.getId())) {
            throw new FeedbackNotFoundException("Không tìm thấy phản hồi với id: " + feedback.getId());
        }
        feedbackRepository.save(feedback);
    }

    @Override
    @Transactional
    public void deleteFeedback(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID phản hồi không được để trống");
        }
        if (!feedbackRepository.existsById(id)) {
            throw new FeedbackNotFoundException("Không tìm thấy phản hồi với id: " + id);
        }
        feedbackRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void markFeedbackAsResolved(Long id) {
        Feedback feedback = getFeedbackById(id);
        feedback.setStatus("RESOLVED");
        feedback.setResolvedAt(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    @Override
    @Transactional
    public void addResponse(Long id, String response, User responder) {
        if (!StringUtils.hasText(response)) {
            throw new IllegalArgumentException("Nội dung phản hồi không được để trống");
        }
        if (responder == null) {
            throw new IllegalArgumentException("Người phản hồi không được để trống");
        }

        Feedback feedback = getFeedbackById(id);
        feedback.setResponse(response);
        feedback.setRespondedBy(responder);
        feedback.setRespondedAt(LocalDateTime.now());
        feedbackRepository.save(feedback);
    }

    @Override
    public double calculateAverageRating() {
        List<Feedback> feedbacks = feedbackRepository.findByOverallRatingGreaterThanEqual(1);
        if (feedbacks.isEmpty()) {
            return 0.0;
        }
        return feedbacks.stream()
                .mapToInt(Feedback::getOverallRating)
                .average()
                .orElse(0.0);
    }

    private void validateFeedbackData(Feedback feedback) {
        if (feedback == null) {
            throw new IllegalArgumentException("Phản hồi không được để trống");
        }
        if (!StringUtils.hasText(feedback.getTitle())) {
            throw new IllegalArgumentException("Tiêu đề phản hồi không được để trống");
        }
        if (!StringUtils.hasText(feedback.getContent())) {
            throw new IllegalArgumentException("Nội dung phản hồi không được để trống");
        }
        if (feedback.getUser() == null) {
            throw new IllegalArgumentException("Người dùng không được để trống");
        }
        if (!StringUtils.hasText(feedback.getType())) {
            throw new IllegalArgumentException("Loại phản hồi không được để trống");
        }
    }
} 