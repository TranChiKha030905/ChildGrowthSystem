package com.childgrowth.tracking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class MedicalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Child child;

    private LocalDateTime eventDate;
    private String eventType; // MILESTONE, ILLNESS, INJURY, MEDICATION, etc.
    private String title;
    private String description;
    
    // For milestones
    private String milestoneCategory; // MOTOR, COGNITIVE, LANGUAGE, SOCIAL, etc.
    private String milestoneDetails;
    
    // For medical events
    private String diagnosis;
    private String treatment;
    private String prescribedBy;
    private String hospital;
    
    // Attachments
    private String photoUrl;
    private String documentUrl;
    
    // Follow-up
    private LocalDateTime followUpDate;
    private String followUpNotes;
    
    // Status
    private boolean isResolved = false;
    private LocalDateTime resolutionDate;
    private String resolutionNotes;
} 