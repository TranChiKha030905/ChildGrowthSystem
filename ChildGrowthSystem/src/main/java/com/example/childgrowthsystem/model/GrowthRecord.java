// GrowthRecord.java (Entity)
package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "growth_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @Column(nullable = false)
    private LocalDateTime measurementDate;
    
    // Basic measurements
    @Column(nullable = false)
    private Double weight; // in kg
    @Column(nullable = false)
    private Double height; // in cm
    @Column(name = "head_circumference")
    private Double headCircumference; // in cm
    
    // Calculated metrics
    @Column(name = "bmi")
    private Double bmi;
    private Double weightForAgePercentile;
    private Double heightForAgePercentile;
    private Double bmiForAgePercentile;
    private Double headCircumferenceForAgePercentile;
    
    // Growth velocity
    private Double weightVelocity; // kg/month
    private Double heightVelocity; // cm/month
    
    // Additional notes
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    private String measurementLocation; // home, clinic, hospital
    private String measuredBy; // parent, doctor, nurse
    
    // Attachments
    private String photoUrl;
    private String documentUrl;
    
    // Status
    @Builder.Default
    private boolean isVerified = false;
    private String verificationNotes;

    @Column(name = "created_at", nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor; // The doctor who recorded this measurement

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}