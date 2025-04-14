package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ActivityRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Child child;

    private LocalDateTime activityDate;
    private String activityType; // PHYSICAL, COGNITIVE, SOCIAL, etc.
    private String activityName;
    private Integer durationMinutes;
    
    // Activity details
    private String location; // indoor, outdoor, playground, etc.
    private String intensity; // low, medium, high
    private String description;
    
    // Development tracking
    private String skillCategory; // motor, cognitive, social, language
    private String skillName;
    private Integer skillLevel; // 1-5 rating
    
    // Activity outcome
    private String outcome;
    private String challenges;
    private String achievements;
    
    // Additional information
    private String notes;
    private String recordedBy;
    private String photoUrl;
    private String videoUrl;
} 