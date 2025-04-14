package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class SleepRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Child child;

    private LocalDateTime sleepStart;
    private LocalDateTime sleepEnd;
    private Integer durationMinutes;
    
    // Sleep quality metrics
    private Integer sleepQuality; // 1-5 rating
    private String sleepLocation; // crib, bed, car, etc.
    private String sleepPosition; // back, side, stomach
    
    // Sleep environment
    private Integer roomTemperature;
    private String roomConditions; // dark, light, noisy, quiet
    
    // Sleep disturbances
    private boolean hadNightmare;
    private boolean hadNightTerrors;
    private boolean hadSleepwalking;
    private String disturbanceNotes;
    
    // Additional notes
    private String notes;
    private String recordedBy; // parent, caregiver
} 