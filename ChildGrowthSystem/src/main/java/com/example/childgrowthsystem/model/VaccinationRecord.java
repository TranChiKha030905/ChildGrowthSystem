package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Child child;

    private LocalDateTime vaccinationDate;
    private String vaccineName;
    private String vaccineType;
    private String batchNumber;
    
    // Vaccination details
    private String administeredBy;
    private String administrationSite;
    private String administrationRoute;
    private Double dose;
    private String doseUnit;
    
    // Schedule information
    private boolean isScheduled;
    private LocalDateTime nextDueDate;
    private Integer doseNumber; // e.g., 1st dose, 2nd dose
    
    // Reaction tracking
    private boolean hadReaction;
    private String reactionType;
    private String reactionSeverity; // mild, moderate, severe
    private String reactionNotes;
    
    // Documentation
    private String certificateUrl;
    private String documentUrl;
    
    // Additional information
    private String notes;
    private String recordedBy;
    private String verifiedBy;
    private LocalDateTime verificationDate;
} 