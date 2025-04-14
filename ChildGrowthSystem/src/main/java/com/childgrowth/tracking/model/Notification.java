package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;
    private String type; // SYSTEM, REMINDER, ALERT, UPDATE
    private String title;
    private String message;
    
    // Notification details
    private String priority; // LOW, MEDIUM, HIGH
    private String category; // GROWTH, HEALTH, VACCINATION, etc.
    private String actionUrl;
    private String actionText;
    
    // Delivery status
    private boolean isEmailSent;
    private boolean isPushSent;
    private boolean isInAppSent;
    private LocalDateTime emailSentAt;
    private LocalDateTime pushSentAt;
    
    // Read status
    private boolean isRead;
    private LocalDateTime readAt;
    
    // Additional information
    private String metadata;
    private boolean isArchived;
    private LocalDateTime archivedAt;
} 