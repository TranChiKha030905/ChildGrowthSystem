package com.example.childgrowthsystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String currency;
    private String billingCycle; // MONTHLY, YEARLY
    
    // Plan features
    private Integer maxChildren;
    private Integer maxStorageGB;
    private boolean hasDoctorConsultation;
    private boolean hasAdvancedAnalytics;
    private boolean hasExportReports;
    private boolean hasAPIAccess;
    
    // Plan limits
    private Integer maxGrowthRecords;
    private Integer maxMedicalEvents;
    private Integer maxPhotos;
    private Integer maxDocuments;
    
    // Plan status
    private boolean isActive;
    private boolean isPopular;
    private Integer displayOrder;
    
    // Promotional information
    private Double promotionalPrice;
    private String promotionalCode;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    
    // Additional information
    private String termsAndConditions;
    private String featuresList;
    private String limitations;
} 