package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    private String category;
    private Integer displayOrder;
    
    // FAQ metadata
    private boolean isActive;
    private boolean isFeatured;
    private String language;
    
    // Usage tracking
    private Integer viewCount;
    private Integer helpfulCount;
    private Integer notHelpfulCount;
    
    // Additional information
    private String tags;
    private String relatedQuestions;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedAt;
    
    // Moderation
    private boolean isApproved;
    private String approvedBy;
    private LocalDateTime approvedAt;
} 